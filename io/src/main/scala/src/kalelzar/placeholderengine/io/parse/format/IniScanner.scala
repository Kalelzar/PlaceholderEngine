package src.kalelzar.placeholderengine.io.parse.format

import src.kalelzar.placeholderengine.io.parse.{Scanner, Token, TokenType}


class IniScanner extends Scanner {

  override val keywords: Map[String, TokenType] = Map.empty[String, TokenType]

  /**
    * @inheritdoc
    * @param c the char to convert
    * @return the token
    */
  override def check(c: Char): Token = {
    c match {
      case '[' => Token(LEFT_BRACKET(), c.toString, line, c.toString) //Beginning of section declaration
      case ']' => Token(RIGHT_BRACKET(), c.toString, line, c.toString) //End of section declaration
      case '=' => Token(EQUALS(), c.toString, line, c.toString) // beginning of value
      case ';' => //comment start
        while (!(peek == '\n' || peek == ';') && !isAtEnd) next //comment end
        if (peek == ';') next
        check(next)
      case ' ' | '\t' | '\r' => check(next) //skip whitespace
      case '\n' =>
        line += 1 //increment line counter
        if (!isAtEnd) check(next) else null //skip newline
      case t => identifier(t) //identifier
    }
  }

  /**
    * Turns a sequence of characters, beginning from the provided Char and
    * until a special character or the end of the file is reached,
    * into an identifier token
    *
    * @param c the starting string
    * @return the token
    */
  def identifier(c: Char): Token = {
    var buffer = c.toString
    while (!isAtEnd && peek != '[' && peek != ']' && peek != ';' && peek != '=' && peek != '\n') buffer += next
    Token(IDENTIFIER(), buffer, line, buffer)
  }
}

/**
  * Token representing '['
  */
case class LEFT_BRACKET() extends TokenType

/**
  * Token representing ']'
  */
case class RIGHT_BRACKET() extends TokenType

/**
  * Token representing '='
  */
case class EQUALS() extends TokenType

/**
  * Token representing a unspecified sequence of characters
  */
case class IDENTIFIER() extends TokenType