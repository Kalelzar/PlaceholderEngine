package src.kalelzar.placeholderengine.io.format

import src.kalelzar.placeholderengine.io.print.format.NodeBasedFilePrinter
import src.kalelzar.placeholderengine.io.{Node, NodeBasedFile}

class IniFile extends NodeBasedFile {
  /**
    * Add a new section with the name provided
    * @param section the name of the section
    */
  def addSection(section: String): Unit = {
    val node = new Node[Seq[Node[_]]](section)
    node.value = Seq[Node[_]]()
    node.hasChildren = true
    addRootNode(node)
  }

  /**
    * Return the section with the name provided
    * @param section the section name
    * @return the section
    */
  def apply(section: String): Node[Seq[Node[_]]] = getRoot.find(_.name == section).get.asInstanceOf[Node[Seq[Node[_]]]]

  /**
    * Adds the value to the provided section
    * @param section the section
    * @param name the name of the value
    * @param value the value of the value
    * @tparam A the type of the value
    */
  def addValueToSection[A](section: String, name: String, value: A): Unit = {
    val node = new Node[A](name)
    node.value = value
    val parent = getRoot.find(_.name == section)
    if (parent.isDefined) {
      if (parent.get.isInstanceOf[Node[Seq[Node[_]]]]) {
        val gparent = parent.get.asInstanceOf[Node[Seq[Node[_]]]]
        addNode(gparent, node)
      } else throw new ClassCastException(s"Parent node ${parent.get.name} cannot contain other nodes.")

    } else throw new NoSuchElementException(s"Section $section does not exist.")
  }

  /**
    *
    * @return a formatted string representation of the file
    */
  override def mkString: String = IniFile.printer.mkString(this)
}

object IniFile {
  val printer = new NodeBasedFilePrinter
}
