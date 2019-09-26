package src.kalelzar.placeholderengine.io

import src.kalelzar.placeholderengine.common.exception.PlaceholderEngineParsingException
import src.kalelzar.placeholderengine.io.format.TextFile
import src.kalelzar.placeholderengine.io.parse.format.{ASSParser, IniParser, XMLParser}

/**
  * Convenience singleton for the opening of files.
  */
object IO {

  /**
    * Opens the file as the provided fileType.
    * If the fileType is unrecognised it opens the file as a plain-text file.
    * @param path the path to the file
    * @param fileType the fileType
    * @return the parsed file
    */
  def open(path: String, fileType: String): PlaceholderEngineFile = {
    val data: Resource[_] = PathResolver.resolve(path)
    try {
      parse(fileType, data)
    } catch {
      case e: PlaceholderEngineParsingException =>
        val r = new RuntimeException(s"Parse error in file $path: ${e.getMessage}")
        r.setStackTrace(Array.empty[StackTraceElement])
        throw r
      case e => throw new RuntimeException(e)
    }
  }

  /**
    * Parses the resource as if it is the of the provided file type.
    * @param fileType the file type
    * @param data the data to parse
    * @return the parsed file
    */
  def parse(fileType: String, data: Resource[_]): PlaceholderEngineFile = {
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
