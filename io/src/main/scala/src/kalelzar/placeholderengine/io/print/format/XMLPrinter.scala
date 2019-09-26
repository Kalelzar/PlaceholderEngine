package src.kalelzar.placeholderengine.io.print.format

import src.kalelzar.placeholderengine.io.Node
import src.kalelzar.placeholderengine.io.format.XMLFile
import src.kalelzar.placeholderengine.io.print.PrettyPrinter

/**
  * A pretty printer for xml files
  */
class XMLPrinter extends PrettyPrinter[XMLFile] {
  /**
    * a reference to the character "
    * because escaping it in a string wouldn't compile for some reason
    * and I don't have time to debug it.
    */
  private val str = '"'

  /**
    * @inheritdoc
    * @param v the file
    * @return the string
    */
  override def mkString(v: XMLFile): String = s"<?xml version=$str${v.xmlVersion}$str encoding=$str${v.xmlEncoding}$str?>\n" + v.getRoot.map(x => mapper(x)(1)).mkString("\n")

  /**
    * Turns node into string
    * @param n the node to convert to string
    * @param indent the current indentation level
    * @return the converted string
    */
  private def mapper(n: Node[_])(indent: Int = 1): String = {
    val pr = if (indent - 2 < 0) 0 else indent - 1
    var attr = n.attributes.map(x => s"${x._1}=$str${x._2}$str").mkString(" ", " ", "")
    if (attr == " ") attr = ""
    if (n.hasChildren) {
      s"<${n.name}$attr>\n" + n.asInstanceOf[Node[Seq[Node[_]]]].value.map(x => mapper(x)(indent + 1)).mkString("  " * indent, "  " * indent, "  " * pr) + s"</${n.name}>\n" + "  " * (pr - 2)
    } else {
      if (n.value != null)
        n.value.toString + "\n"
      else s"<${n.name}$attr/>\n"
    }
  }
}
