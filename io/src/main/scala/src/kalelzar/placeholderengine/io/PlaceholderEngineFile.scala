package src.kalelzar.placeholderengine.io

/**
  * Parent of all file types supported by PlaceholderEngine
  * Exposes behavior common to all files such as saving
  */
trait PlaceholderEngineFile {

  /**
    * Saves this file to the path provided using the formatted
    * string representation of the file from
    * {@link src.kalelzar.placeholderengine.io.PlaceholderEngineFile#mkString mkString}
    *
    * @param path the path
    */
  def save(path: String): Unit = {
    PathResolver.resolve(path).write(mkString)
  }

  /**
    *
    * @return a formatted string representation of the file
    */
  def mkString: String
}
