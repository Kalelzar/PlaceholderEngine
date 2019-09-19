package src.kalelzar.placeholderengine.interface.util

import scala.collection.mutable

trait ICallback {

  private val callbacks = mutable.HashMap[String, Seq[Unit]]()

  def registerCallback(name: String, f: => Unit): Unit = {
    println(f)
    if (callbacks.contains(name)) callbacks(name) = callbacks(name) :+ f
    else callbacks += ((name, Seq(f)))
  }

  def unregisterCallback(name: String, f: => Unit): Unit = {
    if (callbacks.contains(name) && callbacks(name).contains(f)) callbacks(name) = callbacks(name).filter(_ != f)
  }

  protected def callback(name: String, param: Any*): Unit = {
    if (callbacks.contains(name)) callbacks(name).foreach(_ (param))
  }

}
