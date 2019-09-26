package src.kalelzar.placeholderengine.io.parse.format

import src.kalelzar.placeholderengine.io.format.ASSFile

/**
  * A parser for Action Script Sucks (temp) files
  */
class ASSParser {

  /**
    * Parse the string into an Action Script Sucks (temp) file
    * @param string the string to parse
    * @return the parsed file
    */
  def parse(string: String): ASSFile = {
    val assfile = new ASSFile()
    string.split("\n").foreach(assfile.appendLine)
    assfile
  }

}
