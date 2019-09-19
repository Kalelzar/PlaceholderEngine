package src.kalelzar.placeholderengine.io.parse

trait TokenType {

  override def equals(obj: Any): Boolean = toString == obj.toString

  override def toString: String = getClass.getSimpleName
}

