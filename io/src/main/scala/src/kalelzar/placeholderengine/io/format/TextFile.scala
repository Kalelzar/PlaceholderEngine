package src.kalelzar.placeholderengine.io.format

import src.kalelzar.placeholderengine.io.PlaceholderEngineFile

/**
  * A plain text file.
  */
class TextFile extends PlaceholderEngineFile {
  private var lines = List[String]()

  /**
    * appends a new line
    * @param str the new line
    */
  def appendLine(str: String): Unit = {
    lines = lines :+ str
  }

  /**
    * appends the provided string to the last line.
    * @param str the string
    */
  def append(str: String): Unit = {
    lines = lines.init :+ (lines.last + str)
  }

  /**
    *
    * @return an iterator of all lines
    */
  def iterator: Iterator[String] = lines.iterator

  /**
    * @return a formatted string representation of the file
    */
  override def mkString: String = lines.mkString("\n")
}

object TextFile {
  /**
    * Parse the string into a plain-text file.
    * @param s the string
    * @return the text file
    */
  def apply(s: String): TextFile = {
    val tf = new TextFile()
    s.split("\n").foreach(tf.appendLine)
    tf
  }
}
