package src.kalelzar.placeholderengine.io.parse

/**
  * A token to be used by Scanners and Parsers.
  * @param tokenType the type of the token
  * @param lexeme the raw string representation
  * @param line the line the token was on
  * @param value the value of the token
  */
case class Token(tokenType: TokenType, lexeme: String, line: Int, value: String)
