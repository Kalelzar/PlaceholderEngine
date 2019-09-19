package src.kalelzar.placeholderengine.io.parse

import src.kalelzar.placeholderengine.io.NodeBasedFile


trait NodeParser {

  private val scanner: Scanner
  private var tokens = Seq[Token]()
  private var index = 0

  def reset(): Unit = {
    tokens = Seq[Token]()
    index = 0
  }

  def matches(t: TokenType, msg: String): Token = {
    if (check(t)) next
    else throw new PlaceholderEngineParsingException(msg + s". Found $peek", peek.line)
  }

  def next: Token = {
    index += 1
    tokens(index - 1)
  }

  def check(t: TokenType): Boolean = {
    peek.tokenType == t
  }

  def peek: Token = tokens(index)

  def previous: Token = tokens(index - 1)

  def parse(string: String): NodeBasedFile = {
    scanner.setText(string)
    scanner.reset()
    tokens = scanner.scan()
    logic()
  }

  def isAtEnd: Boolean = tokens.length <= index

  def logic(): NodeBasedFile

}
