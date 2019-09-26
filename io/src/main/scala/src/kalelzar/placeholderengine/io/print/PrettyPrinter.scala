package src.kalelzar.placeholderengine.io.print

/**
  * Interface for the creation of pretty printers
  * @tparam A the file type this printer has to print
  */
trait PrettyPrinter[A] {
  /**
    * Print the pretty-printed string representation of the provided file to stdout
    * @param v the file to print
    */
  def print(v: A): Unit = println(mkString(v))

  /**
    * Returns a pretty-printed string representation of the provided file
    * @param v the file
    * @return the string
    */
  def mkString(v: A): String
}
