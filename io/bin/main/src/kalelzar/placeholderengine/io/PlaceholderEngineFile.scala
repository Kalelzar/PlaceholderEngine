package src.kalelzar.placeholderengine.io

trait PlaceholderEngineFile {
  def save(path: String): Unit = {
    PathResolver.resolve(path).write(mkString)
  }

  def mkString: String
}
