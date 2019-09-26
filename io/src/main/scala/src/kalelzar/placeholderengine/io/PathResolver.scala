package src.kalelzar.placeholderengine.io

/**
  * PathResolver allows each runtime to use its own method of loading a file
  * while simultaneously letting users not bother themselves with the specifics
  */
trait PathResolver {
  /**
    * Resolve the path into a resource.
    * @param path the path to resolve
    * @return the resource
    */
  def resolve(path: String): Resource[_]
}

object PathResolver {

  private var _resolver: PathResolver = _

  /**
    * Resolves the resource located at the provided path with the current resolver
    *
    * @param path the path to the resource
    * @return the resolved resource
    */
  def resolve(path: String): Resource[_] = resolver.resolve(path)

  /**
    *
    * @return the resolver currently in use
    */
  def resolver: PathResolver = _resolver

  /**
    *
    * @param resolver the new resolver
    */
  def resolver_=(resolver: PathResolver): Unit = _resolver = resolver
}
