package src.kalelzar.placeholderengine.io.format

import src.kalelzar.placeholderengine.io.PlaceholderEngineFile

class TextFile extends PlaceholderEngineFile {
  private var lines = List[String]()

  def append(str: String): Unit = {
    lines = lines :+ str
  }

  def iterator: Iterator[String] = lines.iterator

  override def mkString: String = lines.mkString("\n")
}

object TextFile {
  def apply(s: String): TextFile = {
    val tf = new TextFile()
    s.split("\n").foreach(tf.append)
    tf
  }
}
