package src.kalelzar.placeholderengine.io.parse

class PlaceholderEngineParsingException(msg: String, line: Int) extends RuntimeException(s"[line $line] $msg")
