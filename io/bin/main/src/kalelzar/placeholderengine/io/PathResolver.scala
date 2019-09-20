package src.kalelzar.placeholderengine.io

trait PathResolver {
  def resolve(path: String): Resource[_]
}

object PathResolver {
  private var _resolver: PathResolver = _

  def resolve(path: String): Resource[_] = resolver.resolve(path)

  def resolver: PathResolver = _resolver

  def resolver_=(resolver: PathResolver): Unit = _resolver = resolver
}
