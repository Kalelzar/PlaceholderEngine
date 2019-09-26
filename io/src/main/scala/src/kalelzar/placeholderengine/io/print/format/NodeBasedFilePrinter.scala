package src.kalelzar.placeholderengine.io.print.format

import src.kalelzar.placeholderengine.io.print.PrettyPrinter
import src.kalelzar.placeholderengine.io.{Node, NodeBasedFile}


class NodeBasedFilePrinter extends PrettyPrinter[NodeBasedFile] {
  /**
    * @inheritdoc
    * @param v the file
    * @return the string
    */
  override def mkString(v: NodeBasedFile): String = {
    v.getRoot.map(x => mapper(x)(1)).mkString("\n")
  }

  /**
    * Turns node into string
    * @param n the node to convert to string
    * @param indent the current indentation level
    * @return the converted string
    */
  def mapper(n: Node[_])(indent: Int = 1): String = {
    var attr = n.attributes.map(x => s"${x._1}='${x._2}'").mkString(" ", ", ", "")
    if (attr == " ") attr = ""
    if (n.hasChildren) {
      s"[${n.name}$attr]\n" + n.asInstanceOf[Node[Seq[Node[_]]]].value.map(x => mapper(x)(indent + 1)).mkString("  " * indent, "\n" + "  " * indent, "")
    } else {
      s"(${n.name}$attr)" + " = " + (if (n.value != null) n.value.toString else "null")
    }
  }
}
