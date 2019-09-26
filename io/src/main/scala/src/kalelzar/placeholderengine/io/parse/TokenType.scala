package src.kalelzar.placeholderengine.io.parse

/**
  * Discount enum for token types. Unlike enums you can add new values to it by extending it.
  */
trait TokenType {

  override def equals(obj: Any): Boolean = toString == obj.toString

  override def toString: String = getClass.getSimpleName
}

