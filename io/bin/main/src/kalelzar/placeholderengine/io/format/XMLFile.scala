package src.kalelzar.placeholderengine.io.format

import src.kalelzar.placeholderengine.io.print.format.XMLPrinter
import src.kalelzar.placeholderengine.io.{Node, NodeBasedFile}

class XMLFile extends NodeBasedFile {


  private var _xmlVersion: String = "1.0"
  private var _xmlEncoding: String = "UTF-8"

  def xmlVersion: String = _xmlVersion

  def xmlVersion_=(xmlVersion: String): Unit = _xmlVersion = xmlVersion

  def xmlEncoding: String = _xmlEncoding

  def xmlEncoding_=(xmlEncoding: String): Unit = _xmlEncoding = xmlEncoding

  def findNodesWithName(name: String): Seq[Node[_]] = {
    findNodesWithName(name, getRoot)
  }

  def findNodesWithName(name: String, parents: Seq[Node[_]]): Seq[Node[_]] = {
    val nodes = flattenedNodeTree(parents)
    nodes.filter(_.name == name)
  }

  def findNodesWithAttribute(attribute: String): Seq[Node[_]] = {
    findNodesWithAttribute(attribute, getRoot)
  }

  def findNodesWithAttribute(attribute: String, parents: Seq[Node[_]]): Seq[Node[_]] = {
    val nodes = flattenedNodeTree(parents)
    nodes.filter(_.hasAttribute(attribute))
  }

  def flattenedNodeTree(parents: Seq[Node[_]]): Seq[Node[_]] = {
    var repeat = true
    var nodes = parents
    while (repeat) {
      repeat = false
      nodes = nodes.flatMap {
        xs =>
          if (xs.hasChildren) {
            repeat = true
            xs.value.asInstanceOf[Seq[Node[_]]]
          }
          else Seq(xs)
      }.distinct.filter(_ != null)
    }
    nodes
  }

  override def mkString: String = XMLFile.printer.mkString(this)
}

object XMLFile {
  val printer = new XMLPrinter
}