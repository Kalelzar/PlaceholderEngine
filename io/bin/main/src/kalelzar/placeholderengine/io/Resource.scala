package src.kalelzar.placeholderengine.io

trait Resource[A] {
  private var _path: String = ""
  private var _source: A = _

  def path: String = _path

  protected def path_=(path: String): Unit = _path = path

  def source: A = _source

  protected def source_=(source: A): Unit = _source = source

  def write(data: String): Unit

  def append(data: String): Unit

  def read: String
}
