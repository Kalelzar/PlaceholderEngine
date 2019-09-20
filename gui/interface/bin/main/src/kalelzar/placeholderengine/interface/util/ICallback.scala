package src.kalelzar.placeholderengine.interface.util

import scala.collection.mutable

trait ICallback {

  private val callbacks = mutable.HashMap[String, Seq[Callback]]()

  def registerCallback(name: String, f: Callback): Unit = {
    println(f)
    if (callbacks.contains(name)) callbacks(name) = callbacks(name) :+ f
    else callbacks += ((name, Seq(f)))
  }

  def unregisterCallback(name: String, f: Callback): Unit = {
    if (callbacks.contains(name) && callbacks(name).contains(f)) callbacks(name) = callbacks(name).filter(_ != f)
  }

  protected def callback(name: String, param: Any*): Unit = {
    if (callbacks.contains(name)) callbacks(name).foreach(_(param))
  }

}

trait Callback{
  def apply(p: Seq[Any]): Unit
  def asCallback: Callback = this
}

object Callback{

  implicit class F0Callback(f: () => Unit) extends Callback {
    override def execute(p: Seq[Any]): Unit = f()
  }

  implicit class F1Callback[A](f: A => Unit) extends Callback {
    override def execute(p: Seq[Any]): Unit = 
      f(p(0).asInstanceOf[A])
  }

  implicit class F2Callback[A,B](f: (A, B) => Unit) extends Callback {
    override def execute(p: Seq[Any]): Unit = 
      f(p(0).asInstanceOf[A], 
        p(1).asInstanceOf[B])
  }

  implicit class F3Callback[A,B,C](f: (A, B, C) => Unit) extends Callback {
    override def execute(p: Seq[Any]): Unit = 
      f(p(0).asInstanceOf[A], 
        p(1).asInstanceOf[B].
        p(2).asInstanceOf[C])
  }
}


