package test.src.kalelzar.placeholderengine.io

import src.kalelzar.placeholderengine.io.{JavaIOPathResolver, PathResolver}
import src.kalelzar.placeholderengine.test.PlaceholderEngineTest
import src.kalelzar.placeholderengine.test.PlaceholderEngineTestFunctions._

class PathResolver_resolveTest extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    PathResolver.resolver = new JavaIOPathResolver
    any.asInstanceOf[Seq[(String, String)]].foreach {
      case (uri, text) =>
        val res = PathResolver.resolve(uri)
        res.write(text)
        val read = res.read.trim()
        assertEqual(read, text, "Text has either been read or written incorrectly")
    }

  }


  override def arguments: Seq[Any] = {
    (0 to 200).map {
      x =>
        (s"testRes/$x.txt", s"$x times written.")
    }
  }

}
