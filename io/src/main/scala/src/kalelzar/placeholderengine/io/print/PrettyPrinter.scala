package src.kalelzar.placeholderengine.io.print

trait PrettyPrinter[A] {
  def print(v: A): Unit = println(mkString(v))

  def mkString(v: A): String
}
