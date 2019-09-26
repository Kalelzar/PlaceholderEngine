package src.kalelzar.placeholderengine.io

import src.kalelzar.placeholderengine.io.parse.NodeParser

import scala.collection.mutable

/**
  * Generic implementation of files that should be represented as a node-like structure
  */
trait NodeBasedFile extends PlaceholderEngineFile {

  /**
    * List of root-level nodes
    */
  private val root = mutable.ListBuffer[Node[_]]()

  /**
    * Reference to the parser that is to be used to process this file
    */
  private var _parser: NodeParser = _

  /**
    * getter for _parser
    * @return the parser
    */
  def parser: NodeParser = _parser

  /**
    * setter for parser
    * @param parser the new parser
    */
  def parser_=(parser: NodeParser): Unit = _parser = parser

  /**
    * Add a new child node to the provided parent node
    * @param parent the parent
    * @param child the child
    */
  def addNode(parent: Node[Seq[Node[_]]], child: Node[_]): Unit = {
    if (parent != null) parent.value = parent.value ++ Seq(child)
    child.parent = parent
  }

  /**
    * Add multiple new child nodes to the provided parent node
    * @param parent
    * @param children
    */
  def addNodes(parent: Node[Seq[Node[_]]], children: Node[_]*): Unit = {
    if (parent != null) {
      val seq = parent.value
      val newSeq = seq ++ children
      parent.value = newSeq
    }
    children.foreach(_.parent = parent)
  }

  /**
    * Add a node to the list of root nodes
    * @param node the root node
    */
  def addRootNode(node: Node[_]): Unit = {
    root += node
  }

  /**
    * Add multiple nodes to the list of root nodes
    * @param nodes the root nodes
    */
  def addRootNodes(nodes: Node[_]*): Unit = {
    root ++= nodes
  }

  /**
    *
    * @return the list of root nodes
    */
  def getRoot: Seq[Node[_]] = root.toSeq
}
