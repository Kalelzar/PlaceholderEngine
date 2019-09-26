package src.kalelzar.placeholderengine.io.parse.format

import src.kalelzar.placeholderengine.io.format.IniFile
import src.kalelzar.placeholderengine.io.parse.{NodeParser, Scanner, Token}

class IniParser extends NodeParser {
  override val scanner: Scanner = new IniScanner
  var currentFile = new IniFile
  var currentSection = ""

  /**
    * @inheritdoc
    * @param string the string
    * @return the file
    */
  override def parse(string: String): IniFile = {
    val r = super.parse(string)
    r.asInstanceOf[IniFile]
  }

  /**
    * @inheritdoc
    */
  override def reset(): Unit = {
    super.reset()
    currentFile = new IniFile
    currentSection = ""
  }

  /**
    * @inheritdoc
    * @return the parsed file
    */
  override def logic(): IniFile = {
    while (!isAtEnd) {
      next match {
        case Token(LEFT_BRACKET(), _, _, _) => section()
        case Token(IDENTIFIER(), _, _, _) => value()
      }
    }
    currentFile
  }

  /**
    * Parses a section
    */
  protected def section(): Unit = {
    val name = matches(IDENTIFIER(), "Expect name of section.")
    //println(s"Parsing section ${name.lexeme}")
    matches(RIGHT_BRACKET(), "Expect ']' after name of section.")
    currentFile.addSection(name.lexeme)
    currentSection = name.lexeme
  }

  /**
    * Parses a value
    */
  protected def value(): Unit = {
    val name = current.lexeme.trim()
    //println(s"Parsing value $name in section ${currentSection}")
    matches(EQUALS(), "Expect '=' after key name.")
    val value = matches(IDENTIFIER(), "Expect value").lexeme
    //println(s"$name = $value")
    currentFile.addValueToSection[String](currentSection, name, value)
  }
}
