package src.kalelzar.placeholderengine.io.parse

import src.kalelzar.placeholderengine.common.exception.PlaceholderEngineParsingException
import src.kalelzar.placeholderengine.io.NodeBasedFile


trait NodeParser {

  protected val scanner: Scanner
  private var tokens = Seq[Token]()
  private var index = 0

  /**
    * Reset the parser
    */
  def reset(): Unit = {
    tokens = Seq[Token]()
    index = 0
  }

  /**
    * Checks if the next token matches the provided token type and if it does returns it.
    * If it doesn't then it throws an exception with the provided error message.
    * @param t the token type
    * @param msg the message
    * @return the next token
    */
  def matches(t: TokenType, msg: String): Token = {
    if (check(t)) next
    else throw new PlaceholderEngineParsingException(msg + s". Found $peek", peek.line)
  }

  /**
    * Returns the next token. Increments the index.
    * @return the next token
    */
  def next: Token = {
    index += 1
    tokens(index - 1)
  }

  /**
    * Checks if the next token matches the provided token type
    * @param t the token type
    * @return true or false
    */
  def check(t: TokenType): Boolean = {
    peek.tokenType == t
  }

  /**
    * Return the next token. Does NOT increment the index.
    * @return
    */
  def peek: Token = tokens(index)

  /**
    * Returns the current token
    * @return the current token
    */
  def current: Token = tokens(index - 1)

  /**
    * Parses the provided string into a file
    * @param string the string
    * @return the file
    */
  def parse(string: String): NodeBasedFile = {
    scanner.setText(string)
    scanner.reset()
    tokens = scanner.scan()
    logic()
  }

  /**
    * Has the parser reached the end
    * @return the end?
    */
  def isAtEnd: Boolean = tokens.length <= index

  /**
    * The logic of the parser
    * @return the parsed file
    */
  def logic(): NodeBasedFile

}
