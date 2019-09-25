package src.kalelzar.placeholderengine.io.parse.format

import src.kalelzar.placeholderengine.io.parse.{Scanner, Token, TokenType}

class XMLScanner extends Scanner {

  override val keywords: Map[String, TokenType] = Map.empty[String, TokenType]

  /**
    * @inheritdoc
    * @param c the char to convert
    * @return the token
    */
  override def check(c: Char): Token = {
    c match {
      case '<' =>
        println(peek)
        if (peek == '/') {
          next
          Token(CLOSING_LEFT_ARROW(), c.toString + "/", line, c.toString + "/") //start of closing tag
        } else if (peek == '?') {
          next
          Token(QLEFT_ARROW(), c.toString + "?", line, c.toString + "?") //start of xml header
        } else Token(LEFT_ARROW(), c.toString, line, c.toString) //start of opening tag
      case '>' =>
        Token(RIGHT_ARROW(), c.toString, line, c.toString) // end of opening tag
      case '/' =>
        if (peek == '>') {
          next
          Token(CLOSING_RIGHT_ARROW(), c.toString + ">", line, c.toString + ">") // end of closing tag
        } else identifier('/') // identifier '/'
      case '?' =>
        if (peek == '>') {
          next
          Token(QRIGHT_ARROW(), c.toString + ">", line, c.toString + ">") // end of xml header
        } else identifier('?') // identifier '?"
      case '=' =>
        var b = ""
        while (peek == ' ' || peek == '\t' || peek == '\r') b += next
        if (peek == '"') Token(EQUALS(), c.toString, line, c.toString) // attribute definition
        else identifier(s"=$b") // just a string
      case ' ' | '\t' | '\r' => check(next) // skip whitespace FIX: This is not compliant with the xml specification
      case '\n' =>
        line += 1 // increment the line number
        if (!isAtEnd) check(next) else null //skip new line
      case t => identifier(t) // identifier
    }
  }

  /**
    * Convenience method for calling identifier with a char
    *
    * @param c the char
    * @return the token
    */
  def identifier(c: Char): Token = {
    val buffer = c.toString
    identifier(buffer)
  }

  /**
    * Turns a sequence of characters, beginning from the provided String and
    * until a special character or the end of the file is reached,
    * into an identifier token
    *
    * @param c the starting string
    * @return the token
    */
  def identifier(c: String): Token = {
    var buffer = c
    if (c == "\"") {
      while (!isAtEnd && peek != '"') buffer += next
      next
      buffer = buffer.drop(1)
    }
    else while (!isAtEnd && peek != '<' && peek != '>' && peek != ' ' && peek != '=' && peek != '\n' && peek != '/') buffer += next
    Token(IDENTIFIER(), buffer, line, buffer)
  }
}

/**
  * Token representing the start of an opening tag - '<'
  */
case class LEFT_ARROW() extends TokenType

/**
  * Token representing the end of an opening or closing tag - '>'
  */
case class RIGHT_ARROW() extends TokenType

/**
  * Token representing the start of an xml header - '<?'
  */
case class QLEFT_ARROW() extends TokenType

/**
  * Token representing the start of an xml header - '?>'
  */
case class QRIGHT_ARROW() extends TokenType

/**
  * Token representing the start of a closing tag - '</'
  */
case class CLOSING_LEFT_ARROW() extends TokenType

/**
  * Token representing the closing of an opening tag - '/>'
  */
case class CLOSING_RIGHT_ARROW() extends TokenType