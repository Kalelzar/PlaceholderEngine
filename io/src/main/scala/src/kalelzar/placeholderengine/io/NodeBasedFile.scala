package src.kalelzar.placeholderengine.io

import src.kalelzar.placeholderengine.io.parse.NodeParser

import scala.collection.mutable

trait NodeBasedFile extends PlaceholderEngineFile {

  private val root = mutable.ListBuffer[Node[_]]()

  private var _parser: NodeParser = _

  def parser: NodeParser = _parser

  def parser_=(parser: NodeParser): Unit = _parser = parser

  def addNode(parent: Node[Seq[Node[_]]], child: Node[_]): Unit = {
    if (parent != null) parent.value = parent.value ++ Seq(child)
    child.parent = parent
  }

  def addNodes(parent: Node[Seq[Node[_]]], children: Node[_]*): Unit = {
    if (parent != null) {
      val seq = parent.value
      val newSeq = seq ++ children
      parent.value = newSeq
    }
    children.foreach(_.parent = parent)
  }

  def addRootNode(node: Node[_]): Unit = {
    root += node
  }

  def addRootNodes(nodes: Node[_]*): Unit = {
    root ++= nodes
  }

  def getRoot: Seq[Node[_]] = root.toSeq
}
