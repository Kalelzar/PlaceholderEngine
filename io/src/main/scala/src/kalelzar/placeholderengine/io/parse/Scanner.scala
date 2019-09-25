package src.kalelzar.placeholderengine.io.parse

import scala.collection.mutable

/**
  * A generic scanner interface that can be used to create overcomplicated and badly performing tokenizers
  */
trait Scanner {
  /**
    * a map of all special keywords in the file format
    */
  protected val keywords: Map[String, TokenType]
  /**
    * the current line
    */
  protected var line = 1
  /**
    * the current index
    */
  protected var index = 0
  /**
    * the text to scan
    */
  protected var text = ""

  /**
    * Returns the next character in the text and advances the index.
    * If the EOF has been reached returns ETX
    *
    * @return the next char
    */
  def next: Char = {
    if (!isAtEnd) {
      index += 1
      text(index - 1)
    } else 3.toChar
  }

  /**
    *
    * @return has the scanner reached EOF
    */
  def isAtEnd: Boolean = {
    text.length <= index
  }

  /**
    * Returns the next character in the text without advancing the index.
    * If the EOF has been reached returns ETX
    *
    * @return the next char
    */
  def peek: Char = {
    if (!isAtEnd) {
      text(index)
    } else 3.toChar
  }

  /**
    * Sets the text to scan. Also resets the scanner by invoking
    * {@link src.kalelzar.placeholderengine.io.parse.Scanner#reset() reset}
    *
    * @param text the new text to scan
    */
  def setText(text: String): Unit = {
    reset()
    this.text = text
  }

  /**
    * Resets the scanner back to line 1 and index 0
    */
  def reset(): Unit = {
    line = 1
    index = 0
  }

  /**
    * Tokenizes the given text
    *
    * @return a list of tokens
    */
  def scan(): Seq[Token] = {
    val res = mutable.ListBuffer[Token]()
    while (!isAtEnd) {
      res += check(next)
    }
    res.filter(_ != null).result()
  }

  /**
    * Converts a character or a sequence of characters to a token
    *
    * @param c the char to convert
    * @return the token
    */
  def check(c: Char): Token


}
