package src.kalelzar.placeholderengine.interface.util

import scala.collection.mutable

trait Callback{
  def apply(p: Seq[Any]): Unit ={
    try{
      execute(p)
    }catch{
      //TODO: Add special handling of ClassCastException
      case e: ClassCastException => throw new RuntimeException(e)
      case e => throw new RuntimeException(e)
    }
  }
  def execute(p: Seq[Any]): Unit
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
        p(1).asInstanceOf[B],
        p(2).asInstanceOf[C])
  }
}


