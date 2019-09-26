package src.kalelzar.placeholderengine.io.format

import src.kalelzar.placeholderengine.io.print.format.XMLPrinter
import src.kalelzar.placeholderengine.io.{Node, NodeBasedFile}

/**
  * A XML file.
  */
class XMLFile extends NodeBasedFile {



  private var _xmlVersion: String = "1.0"
  private var _xmlEncoding: String = "UTF-8"

  /**
    * The xml version of this file. Default is 1.0
    * @return the xml version
    */
  def xmlVersion: String = _xmlVersion

  /**
    * Set the xml version
    * @param xmlVersion the new version
    */
  def xmlVersion_=(xmlVersion: String): Unit = _xmlVersion = xmlVersion

  /**
    * The encoding of this file
    * @return the encoding
    */
  def xmlEncoding: String = _xmlEncoding

  /**
    * Change the encoding of this file
    * @param xmlEncoding the new encoding
    */
  def xmlEncoding_=(xmlEncoding: String): Unit = _xmlEncoding = xmlEncoding

  /**
    * Find all nodes with the provided name starting the search from the root node
    * @param name the name
    * @return the nodes
    */
  def findNodesWithName(name: String): Seq[Node[_]] = {
    findNodesWithName(name, getRoot)
  }

  /**
    * Find all nodes with the provided name that are children of the parents provided
    * @param name the name
    * @param parents the parents
    * @return the nodes
    */
  def findNodesWithName(name: String, parents: Seq[Node[_]]): Seq[Node[_]] = {
    val nodes = flattenedNodeTree(parents)
    nodes.filter(_.name == name)
  }

  /**
    * Find all nodes with the provided attribute starting the search from the root node
    * @param attribute the attribute
    * @return the nodes
    */
  def findNodesWithAttribute(attribute: String): Seq[Node[_]] = {
    findNodesWithAttribute(attribute, getRoot)
  }

  /**
    * Find all nodes with the provided attribute that are children of the parents provided
    * @param attribute the attribute
    * @param parents the parents
    * @return the nodes
    */
  def findNodesWithAttribute(attribute: String, parents: Seq[Node[_]]): Seq[Node[_]] = {
    val nodes = flattenedNodeTree(parents)
    nodes.filter(_.hasAttribute(attribute))
  }

  /**
    * Flatten the sequence of parent nodes into a sequence of all their children nodes
    * @param parents the parents
    * @return the flattened tree
    */
  def flattenedNodeTree(parents: Seq[Node[_]]): Seq[Node[_]] = {
    var repeat = true
    var nodes = parents
    while (repeat) {
      repeat = false
      nodes = nodes.flatMap {
        xs =>
          if (xs.hasChildren) {
            repeat = true
            xs.value.asInstanceOf[Seq[Node[_]]]
          }
          else Seq(xs)
      }.distinct.filter(_ != null)
    }
    nodes
  }

  /**
    * @return a formatted string representation of the file
    */
  override def mkString: String = XMLFile.printer.mkString(this)
}

object XMLFile {
  val printer = new XMLPrinter
}