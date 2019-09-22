package src.kalelzar.placeholderengine.interface.util

import scala.collection.mutable

/**
  * Interface allowing the creation of callbacks to the class extending it
  *
  * @tparam A the return type of the callbacks
  */
trait ICallback[A] {

  private val callbacks = mutable.HashMap[String, Seq[Callback[A]]]()

  /**
    * Register the callback with the name provided
    *
    * @param name the name of the callback
    * @param f    the callback
    */
  def registerCallback(name: String, f: Callback[A]): Unit = {
    println(f)
    if (callbacks.contains(name)) callbacks(name) = callbacks(name) :+ f
    else callbacks += ((name, Seq(f)))
  }

  /**
    * Unregister the callback with the name provided
    *
    * @param name the name of the callback
    * @param f    the callback
    */
  def unregisterCallback(name: String, f: Callback[A]): Unit = {
    if (callbacks.contains(name) && callbacks(name).contains(f)) callbacks(name) = callbacks(name).filter(_ != f)
  }

  /**
    * Call all callbacks with the name provided using the provided parameters
    *
    * @param name  the name of the callback
    * @param param the parameters to pass to the callback
    * @return the result of calling the callbacks
    */
  protected def callback(name: String, param: Any*): Seq[A] = {
    if (callbacks.contains(name)) callbacks(name).map(_ (param))
    else Seq.empty[A]
  }

}

/**
  * A callback that can have any number of arguments (up to 22) regardless of the type
  * It sacrifices compile-time type checks in exchange for being a whole lot more convenient to store
  *
  * @tparam A return type of callbacks
  */
trait Callback[A] {
  /**
    * Calls execute with no parameters
    *
    * @return the result of the callback
    */
  def apply(): A = apply(())

  /**
    * Calls execute with the provided parameters
    *
    * @param p the parameters
    * @return the result of the callback
    */
  def apply(p: Any*): A = {
    try {
      execute(p)
    } catch {
      //TODO: Add special handling of ClassCastException
      case e: ClassCastException => throw new RuntimeException(e)
      case e: Exception => throw new RuntimeException(e)
    }
  }

  /**
    * Executes the function used to create the fallback with the provided parameters
    *
    * @param p the parameters
    * @return the result of the callback
    */
  def execute(p: Seq[Any]): A

  /**
    * Returns a reference to this callback
    * Used to easily create callbacks from functions
    *
    * @return this callback
    */
  def asCallback: Callback[A] = this
}

/**
  * Companion object of Callback that contains all implicit classes needed to convert functions to callbacks
  */
object Callback {

  /**
    * Converts functions matching () => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F0Callback[R](f: () => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f()
  }

  /**
    * Converts functions matching (A) => R to callbacks
    *
    * @param f the function   
    * @tparam R the return type
    */
  implicit class F1Callback[A, R](f: A => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A])
  }

  /**
    * Converts functions matching (A, B) => R to callbacks
    *
    * @param f the function   
    * @tparam R the return type
    */
  implicit class F2Callback[A, B, R](f: (A, B) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B])
  }

  /**
    * Converts functions matching (A, B, C) => R to callbacks
    *
    * @param f the function   
    * @tparam R the return type
    */
  implicit class F3Callback[A, B, C, R](f: (A, B, C) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C])
  }

  /**
    * Converts functions matching (A, B, C, D) => R to callbacks
    *
    * @param f the function   
    * @tparam R the return type
    */
  implicit class F4Callback[A, B, C, D, R](f: (A, B, C, D) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D])
  }

  /**
    * Converts functions matching (A, B, C, D, E) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F5Callback[A, B, C, D, E, R](f: (A, B, C, D, E) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F6Callback[A, B, C, D, E, F, R](f: (A, B, C, D, E, F) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F7Callback[A, B, C, D, E, F, G, R](f: (A, B, C, D, E, F, G) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F8Callback[A, B, C, D, E, F, G, H, R](f: (A, B, C, D, E, F, G, H) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F9Callback[A, B, C, D, E, F, G, H, I, R](f: (A, B, C, D, E, F, G, H, I) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F10Callback[A, B, C, D, E, F, G, H, I, J, R](f: (A, B, C, D, E, F, G, H, I, J) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J, K) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F11Callback[A, B, C, D, E, F, G, H, I, J, K, R](f: (A, B, C, D, E, F, G, H, I, J, K) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J, K, L) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F12Callback[A, B, C, D, E, F, G, H, I, J, K, L, R](f: (A, B, C, D, E, F, G, H, I, J, K, L) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J, K, L, M) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F13Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J, K, L, M, N) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F14Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F15Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F16Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F17Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P], p(16).asInstanceOf[Q])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F18Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P], p(16).asInstanceOf[Q], p(17).asInstanceOf[S])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F19Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P], p(16).asInstanceOf[Q], p(17).asInstanceOf[S], p(18).asInstanceOf[T])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F20Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P], p(16).asInstanceOf[Q], p(17).asInstanceOf[S], p(18).asInstanceOf[T], p(19).asInstanceOf[U])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, V) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F21Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, V, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, V) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P], p(16).asInstanceOf[Q], p(17).asInstanceOf[S], p(18).asInstanceOf[T], p(19).asInstanceOf[U], p(20).asInstanceOf[V])
  }

  /**
    * Converts functions matching (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, V, W) => R to callbacks
    *
    * @param f the function
    * @tparam R the return type
    */
  implicit class F22Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, V, W, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, V, W) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p.head.asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P], p(16).asInstanceOf[Q], p(17).asInstanceOf[S], p(18).asInstanceOf[T], p(19).asInstanceOf[U], p(20).asInstanceOf[V], p(21).asInstanceOf[W])
  }

}
