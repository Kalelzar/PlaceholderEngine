package test.src.kalelzar.placeholderengine.io

import src.kalelzar.placeholderengine.io.format.XMLFile
import src.kalelzar.placeholderengine.io.{IO, JavaIOPathResolver, PathResolver}
import src.kalelzar.placeholderengine.test.PlaceholderEngineTest
import src.kalelzar.placeholderengine.test.PlaceholderEngineTestFunctions._

class IO_Open_XMLTest extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    PathResolver.resolver = new JavaIOPathResolver
    val file = IO.open(any.head.toString, "xml").asInstanceOf[XMLFile]
    assertTrue(file.findNodesWithName("hasChild").nonEmpty)
  }

  override def arguments: Seq[Any] = Seq[Any]("testRes/test.xml")
}

class IO_Open_INITest extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    PathResolver.resolver = new JavaIOPathResolver
    val file = IO.open(any.head.toString, "ini")
  }

  override def arguments: Seq[Any] = Seq[Any]("testRes/test.ini")
}
