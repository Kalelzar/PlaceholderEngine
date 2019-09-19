package src.kalelzar.placeholderengine.io.parse

trait Scanner {
  val keywords: Map[String, TokenType]
  var line = 1
  var index = 0
  var text = ""

  def next: Char = {
    if (!isAtEnd) {
      index += 1
      text(index - 1)
    } else 4.toChar
  }

  def isAtEnd: Boolean = {
    text.length <= index
  }

  def peek: Char = {
    if (!isAtEnd) {
      text(index)
    } else 4.toChar
  }

  def reset(): Unit = {
    line = 1
    index = 0
  }

  def setText(text: String): Unit = {
    this.text = text
  }

  def scan(): Seq[Token]


}
