package src.kalelzar.placeholderengine.io.parse.format

import src.kalelzar.placeholderengine.io.parse.{Scanner, Token, TokenType}

import scala.collection.mutable


class IniScanner extends Scanner {

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
      case '[' => Token(LEFT_BRACKET(), c.toString, line, c.toString)
      case ']' => Token(RIGHT_BRACKET(), c.toString, line, c.toString)
      case '=' => Token(EQUALS(), c.toString, line, c.toString)
      case ';' =>
        while (!(peek == '\n' || peek == ';') && !isAtEnd) next
        if (peek == ';') next
        check(next)
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
    var buffer = c.toString
    while (!isAtEnd && peek != '[' && peek != ']' && peek != ';' && peek != '=' && peek != '\n') buffer += next
    Token(IDENTIFIER(), buffer, line, buffer)
  }
}

case class LEFT_BRACKET() extends TokenType

case class RIGHT_BRACKET() extends TokenType

case class EQUALS() extends TokenType

case class IDENTIFIER() extends TokenType