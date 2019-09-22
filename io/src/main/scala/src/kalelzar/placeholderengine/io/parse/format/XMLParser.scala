package src.kalelzar.placeholderengine.io.parse.format

import src.kalelzar.placeholderengine.common.exception.PlaceholderEngineParsingException
import src.kalelzar.placeholderengine.io.Node
import src.kalelzar.placeholderengine.io.format.XMLFile
import src.kalelzar.placeholderengine.io.parse.{NodeParser, Scanner, Token}

import scala.collection.mutable

class XMLParser extends NodeParser {
  override val scanner: Scanner = new XMLScanner
  private val openTags = mutable.Stack[Node[_]]()
  private var rootNode = new Node[Seq[Node[_]]]("root")
  rootNode.hasChildren = true
  rootNode.value = Seq[Node[_]]()
  private var currentNode: Node[_] = rootNode

  private var currentFile = new XMLFile
  private var first = true

  override def parse(string: String): XMLFile = {
    val r = super.parse(string)
    r.asInstanceOf[XMLFile]
  }

  override def reset(): Unit = {
    super.reset()
    currentFile = new XMLFile
    rootNode = new Node[Seq[Node[_]]]("root")
    rootNode.hasChildren = true
    rootNode.value = Seq[Node[_]]()
    currentNode = rootNode
  }

  override def logic(): XMLFile = {
    while (!isAtEnd) {
      next match {
        case Token(QLEFT_ARROW(), _, line, _) =>
          if (!first) throw new PlaceholderEngineParsingException("XML Prolog must be the first element in the document.", line)
          while (!check(QRIGHT_ARROW())) next
          next
        case Token(LEFT_ARROW(), _, _, _) =>
          openTag()
        case Token(CLOSING_LEFT_ARROW(), _, _, _) =>
          closeTag()
        case Token(XML_IDENTIFIER(), lexeme, _, _) =>
          val buff = new mutable.StringBuilder(lexeme)
          while (check(XML_IDENTIFIER())) {
            buff.append(" " + next.lexeme)
          }
          val n = new Node[String]("__inner__")
          n.value = buff.result()
          currentNode.asInstanceOf[Node[Seq[Node[_]]]].value = currentNode.value.asInstanceOf[Seq[Node[_]]] :+ n
        case e => throw new PlaceholderEngineParsingException("Unexpected token " + e, e.line)
      }
      first = false
    }
    rootNode.value.foreach(currentFile.addRootNode)
    currentFile
  }

  def closeTag(): Unit = {
    val name = matches(XML_IDENTIFIER(), "Expected name of tag after '<' ")
    matches(RIGHT_ARROW(), "Expected '>' after name of closing tag")
    if (openTags.isEmpty) throw new PlaceholderEngineParsingException(s"Attempted to close tag ${name.lexeme} before any tag was opened.", name.line)
    if (openTags.top.name != name.lexeme) throw new PlaceholderEngineParsingException(s"Attempted to close tag ${name.lexeme} before ${openTags.top.name}.", name.line)
    openTags.pop()
    if (openTags.isEmpty) currentNode = rootNode
    else currentNode = openTags.top

  }

  def openTag(): Unit = {
    val name = matches(XML_IDENTIFIER(), "Expected name of tag after '<' ")
    val buff = mutable.ListBuffer[(String, String)]()
    while (!isAtEnd && check(XML_IDENTIFIER())) {
      val attrName = next
      matches(XML_EQUALS(), "Expected '=' after attribute name")
      val attrValue = matches(XML_IDENTIFIER(), "Expected identifier after '=' in attribute")
      buff += ((attrName.lexeme, attrValue.lexeme))
    }
    if (check(RIGHT_ARROW())) {
      next
      val n = new Node[Seq[Node[_]]](name.lexeme, buff.toSeq)
      n.hasChildren = true
      n.value = Seq[Node[_]]()
      currentNode.asInstanceOf[Node[Seq[Node[_]]]].value = currentNode.value.asInstanceOf[Seq[Node[_]]] :+ n
      currentNode = n
      openTags.push(n)
    } else if (check(CLOSING_RIGHT_ARROW())) {
      next
      currentNode.asInstanceOf[Node[Seq[Node[_]]]].value = currentNode.value.asInstanceOf[Seq[Node[_]]] :+ new Node[Nothing](name.lexeme, buff.toSeq)
    } else throw new PlaceholderEngineParsingException("Expected either '>' or '/>' to close the tag.", peek.line)
  }

}
