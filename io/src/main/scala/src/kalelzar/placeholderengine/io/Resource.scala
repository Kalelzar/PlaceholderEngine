package src.kalelzar.placeholderengine.io

/**
  * An interface for accessing the implementation of 'File' type resources
  *
  * @tparam A the type of the resource
  */
trait Resource[A] {
  private var _path: String = ""
  private var _source: A = _

  /**
    * @return the path to the resource
    */
  def path: String = _path

  /**
    *
    * @param path the new path to the resource
    */
  protected def path_=(path: String): Unit = _path = path

  /**
    * Writes the provided string to the resource
    * Overwrites any existing content in the resource.
    * <br>If you want to append the string to the end of the resource use
    * {@link src.kalelzar.placeholderengine.io.Resource#append append} instead.
    *
    * @param data the data to write
    */
  def write(data: String): Unit

  /**
    * Writes the provided string to the end of the resource
    * <br>If you want to overwrite all existing content in the resource
    * use {@link src.kalelzar.placeholderengine.io.Resource#write write} instead.
    *
    * @param data the data to write
    */
  def append(data: String): Unit

  /**
    * Reads the all of the content of the resource as a string
    *
    * @return the content
    */
  def read: String

  /**
    * Returns the underlying 'File' implementation so that the resolver may access
    * additional methods not exposed by this interface.
    * Should not be used in non-runtime code
    *
    * @return the 'File' implementation
    */
  private[io] def source: A = _source

  /**
    *
    * @param source the new source
    */
  protected def source_=(source: A): Unit = _source = source
}
