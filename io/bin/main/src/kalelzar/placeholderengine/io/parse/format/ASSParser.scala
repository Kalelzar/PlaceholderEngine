package src.kalelzar.placeholderengine.io.parse.format

import src.kalelzar.placeholderengine.io.format.ASSFile

class ASSParser {

  def parse(string: String): ASSFile = {
    val assfile = new ASSFile()
    string.split("\n").foreach(assfile.append)
    assfile
  }

}
