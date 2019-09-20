package src.kalelzar.placeholderengine.io.parse.format

import src.kalelzar.placeholderengine.io.parse.{Scanner, Token, TokenType}

import scala.collection.mutable

class XMLScanner extends Scanner {
  override val keywords: Map[String, TokenType] = Map.empty[String, TokenType]

  override def scan(): Seq[Token] = {
    val res = mutable.ListBuffer[Token]()
    while (!isAtEnd) {

      res += check(next)

    }

    res.filter(_ != null).result()
  }

  def check(c: Char): Token = {
    c match {
      case '<' =>
        if (peek == '/') {
          next
          Token(CLOSING_LEFT_ARROW(), c.toString + "/", line, c.toString + "/")
        } else if (peek == '?') {
          next
          Token(QLEFT_ARROW(), c.toString + "?", line, c.toString + "?")
        } else Token(LEFT_ARROW(), c.toString, line, c.toString)
      case '>' =>
        Token(RIGHT_ARROW(), c.toString, line, c.toString)
      case '/' =>
        if (peek == '>') {
          next
          Token(CLOSING_RIGHT_ARROW(), c.toString + ">", line, c.toString + ">")
        } else identifier('/')
      case '?' =>
        if (peek == '>') {
          next
          Token(QRIGHT_ARROW(), c.toString + ">", line, c.toString + ">")
        } else identifier('?')
      case '=' =>
        var b = ""
        while (peek == ' ' || peek == '\t' || peek == '\r') b += next
        if (peek == '"') Token(XML_EQUALS(), c.toString, line, c.toString)
        else identifier(s"=$b")
      case ' ' | '\t' | '\r' => check(next)
      case '\n' =>
        line += 1
        if (!isAtEnd) check(next) else {
          null
        }
      case t => identifier(t)
    }
  }

  def identifier(c: Char): Token = {
    val buffer = c.toString
    identifier(buffer)
  }

  def identifier(c: String): Token = {
    var buffer = c
    if (c == "\"") {
      while (!isAtEnd && peek != '"') buffer += next
      next
      buffer = buffer.drop(1)
    }
    else while (!isAtEnd && peek != '<' && peek != '>' && peek != ' ' && peek != '=' && peek != '\n' && peek != '/') buffer += next
    Token(XML_IDENTIFIER(), buffer, line, buffer)
  }
}

case class LEFT_ARROW() extends TokenType

case class RIGHT_ARROW() extends TokenType

case class QLEFT_ARROW() extends TokenType

case class QRIGHT_ARROW() extends TokenType

case class CLOSING_LEFT_ARROW() extends TokenType

case class CLOSING_RIGHT_ARROW() extends TokenType

case class XML_EQUALS() extends TokenType

case class XML_IDENTIFIER() extends TokenType