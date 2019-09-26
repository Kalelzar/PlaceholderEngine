package src.kalelzar.placeholderengine.io

class Node[A](val name: String, val attributes: Seq[(String, String)] = Seq.empty[(String, String)]) {

  /**
    * The value stored in the node
    */
  protected var _value: A = _

  private var _parent: Node[Seq[Node[_]]] = _
  /**
    * Is the type of value Seq[ Node[_] ]?
    */
  private var _hasChildren: Boolean = false

  /**
    * the parent of this node
    * @return the parent
    */
  def parent: Node[Seq[Node[_]]] = _parent

  /**
    * Set the parent of this node to a different one
    * @param parent the new parent
    */
  def parent_=(parent: Node[Seq[Node[_]]]): Unit = _parent = parent

  /**
    * Does this node have an attribute with the provided name
    * @param attr the name
    * @return is there an attribute with the provided name
    */
  def hasAttribute(attr: String): Boolean = attributes.count(_._1 == attr) > 0

  /**
    * Returns Some of the child with the name provided if it is a child of this node
    * or else None
    * @param key the name of the child
    * @return the child
    */
  def apply(key: String): Option[Any] = {
    if (hasChildren) value.asInstanceOf[Seq[Node[A]]].find(_.name == key)
    else Some(value)
  }

  /**
    * @return Does this node have children?
    */
  def hasChildren: Boolean = _hasChildren

  /**
    * Set whether or not this node has children
    * @param hasChildren
    */
  def hasChildren_=(hasChildren: Boolean): Unit = _hasChildren = hasChildren

  /**
    *
    * @return the value of this node
    */
  def apply: A = value

  /**
    *
    * @return a string representation of this node
    */
  override def toString: String = s"{$name = $value, [${attributes.mkString(", ")}]}"

  /**
    *
    * @return the value of this node
    */
  def value: A = _value

  /**
    * Set the value of this node
    * @param value new value
    */
  def value_=(value: A): Unit = _value = value
}
