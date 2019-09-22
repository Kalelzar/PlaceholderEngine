package src.kalelzar.placeholderengine.common.exception

/**
  * An exception thrown by io functions when an error occurs during parsing
  *
  * @param msg  the message of the exception
  * @param line the line in the parsed text the exception occurred on
  */
class PlaceholderEngineParsingException(msg: String, line: Int) extends PlaceholderEngineError(s"[line $line] $msg")
