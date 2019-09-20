package test.src.kalelzar.placeholderengine.io

import src.kalelzar.placeholderengine.io.format.TextFile
import src.kalelzar.placeholderengine.io.{IO, JavaIOPathResolver, PathResolver}
import src.kalelzar.placeholderengine.test.PlaceholderEngineTest
import src.kalelzar.placeholderengine.test.PlaceholderEngineTestFunctions._

class Save_File_Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    PathResolver.resolver = new JavaIOPathResolver
    val file = IO.open(any(0).toString, "txt").asInstanceOf[TextFile]
    file.save(any(0).toString + ".2")
    val file2 = IO.open(any(0).toString + ".2", "txt").asInstanceOf[TextFile]
    assertEqual(file.iterator.mkString("\n"), file2.iterator.mkString("\n"))
  }

  override def arguments: Seq[Any] = Seq("test.txt")
}
