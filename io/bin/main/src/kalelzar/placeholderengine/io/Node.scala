package src.kalelzar.placeholderengine.io

class Node[A](val name: String, val attributes: Seq[(String, String)] = Seq.empty[(String, String)]) {

  protected var _value: A = _
  private var _parent: Node[Seq[Node[_]]] = _
  private var _hasChildren: Boolean = false

  def parent: Node[Seq[Node[_]]] = _parent

  def parent_=(parent: Node[Seq[Node[_]]]): Unit = _parent = parent

  def hasAttribute(attr: String): Boolean = attributes.count(_._1 == attr) > 0

  def apply(key: String): Option[Any] = {
    if (hasChildren) value.asInstanceOf[Seq[Node[A]]].find(_.name == key)
    else Some(value)
  }

  def hasChildren: Boolean = _hasChildren

  def hasChildren_=(hasChildren: Boolean): Unit = _hasChildren = hasChildren

  def apply: A = value

  override def toString: String = s"{$name = $value, [${attributes.mkString(", ")}]}"

  def value: A = _value

  def value_=(value: A): Unit = _value = value
}
