package src.kalelzar.placeholderengine.io

import src.kalelzar.placeholderengine.io.format.TextFile
import src.kalelzar.placeholderengine.io.parse.PlaceholderEngineParsingException
import src.kalelzar.placeholderengine.io.parse.format.{ASSParser, IniParser, XMLParser}

object IO {

  def open(path: String, fileType: String): PlaceholderEngineFile = {
    val data: Resource[_] = PathResolver.resolve(path)
    try {
      resolve(fileType, data)
    } catch {
      case e: PlaceholderEngineParsingException =>
        val r = new RuntimeException(s"Parse error in file $path: ${e.getMessage}")
        r.setStackTrace(Array.empty[StackTraceElement])
        throw r
      case e => throw new RuntimeException(e)
    }
  }

  def resolve(fileType: String, data: Resource[_]): PlaceholderEngineFile = {
    fileType match {
      case "ini" =>
        val ip = new IniParser
        ip.parse(data.read)
      case "ass" =>
        val assp = new ASSParser
        assp.parse(data.read)
      case "xml" =>
        val xmlp = new XMLParser
        xmlp.parse(data.read)
      case x =>
        //Console.err.println(s"Unrecognized file type $x. Returning text file.")
        TextFile(data.read)
    }
  }

}
