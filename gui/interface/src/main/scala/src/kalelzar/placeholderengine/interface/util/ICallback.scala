package src.kalelzar.placeholderengine.interface.util

import scala.collection.mutable

trait ICallback[A] {

  private val callbacks = mutable.HashMap[String, Seq[Callback[A]]]()

  def registerCallback(name: String, f: Callback[A]): Unit = {
    println(f)
    if (callbacks.contains(name)) callbacks(name) = callbacks(name) :+ f
    else callbacks += ((name, Seq(f)))
  }

  def unregisterCallback(name: String, f: Callback[A]): Unit = {
    if (callbacks.contains(name) && callbacks(name).contains(f)) callbacks(name) = callbacks(name).filter(_ != f)
  }

  protected def callback(name: String, param: Any*): Seq[A] = {
    if (callbacks.contains(name)) callbacks(name).map(_ (param))
    else Seq.empty[A]
  }

}

trait Callback[A] {
  def apply(p: Seq[Any]): A = {
    try {
      execute(p)
    } catch {
      //TODO: Add special handling of ClassCastException
      case e: ClassCastException => throw new RuntimeException(e)
      case e => throw new RuntimeException(e)
    }
  }

  def execute(p: Seq[Any]): A

  def asCallback: Callback[A] = this
}

object Callback {

  implicit class F0Callback[R](f: () => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f()
  }

  implicit class F1Callback[A, R](f: (A) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A])
  }

  implicit class F2Callback[A, B, R](f: (A, B) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B])
  }

  implicit class F3Callback[A, B, C, R](f: (A, B, C) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C])
  }

  implicit class F4Callback[A, B, C, D, R](f: (A, B, C, D) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D])
  }

  implicit class F5Callback[A, B, C, D, E, R](f: (A, B, C, D, E) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E])
  }

  implicit class F6Callback[A, B, C, D, E, F, R](f: (A, B, C, D, E, F) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F])
  }

  implicit class F7Callback[A, B, C, D, E, F, G, R](f: (A, B, C, D, E, F, G) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G])
  }

  implicit class F8Callback[A, B, C, D, E, F, G, H, R](f: (A, B, C, D, E, F, G, H) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H])
  }

  implicit class F9Callback[A, B, C, D, E, F, G, H, I, R](f: (A, B, C, D, E, F, G, H, I) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I])
  }

  implicit class F10Callback[A, B, C, D, E, F, G, H, I, J, R](f: (A, B, C, D, E, F, G, H, I, J) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J])
  }

  implicit class F11Callback[A, B, C, D, E, F, G, H, I, J, K, R](f: (A, B, C, D, E, F, G, H, I, J, K) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K])
  }

  implicit class F12Callback[A, B, C, D, E, F, G, H, I, J, K, L, R](f: (A, B, C, D, E, F, G, H, I, J, K, L) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L])
  }

  implicit class F13Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M])
  }

  implicit class F14Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N])
  }

  implicit class F15Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O])
  }

  implicit class F16Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P])
  }

  implicit class F17Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P], p(16).asInstanceOf[Q])
  }

  implicit class F18Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P], p(16).asInstanceOf[Q], p(17).asInstanceOf[S])
  }

  implicit class F19Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P], p(16).asInstanceOf[Q], p(17).asInstanceOf[S], p(18).asInstanceOf[T])
  }

  implicit class F20Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P], p(16).asInstanceOf[Q], p(17).asInstanceOf[S], p(18).asInstanceOf[T], p(19).asInstanceOf[U])
  }

  implicit class F21Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, V, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, V) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P], p(16).asInstanceOf[Q], p(17).asInstanceOf[S], p(18).asInstanceOf[T], p(19).asInstanceOf[U], p(20).asInstanceOf[V])
  }

  implicit class F22Callback[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, V, W, R](f: (A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, S, T, U, V, W) => R) extends Callback[R] {
    override def execute(p: Seq[Any]): R = f(p(0).asInstanceOf[A], p(1).asInstanceOf[B], p(2).asInstanceOf[C], p(3).asInstanceOf[D], p(4).asInstanceOf[E], p(5).asInstanceOf[F], p(6).asInstanceOf[G], p(7).asInstanceOf[H], p(8).asInstanceOf[I], p(9).asInstanceOf[J], p(10).asInstanceOf[K], p(11).asInstanceOf[L], p(12).asInstanceOf[M], p(13).asInstanceOf[N], p(14).asInstanceOf[O], p(15).asInstanceOf[P], p(16).asInstanceOf[Q], p(17).asInstanceOf[S], p(18).asInstanceOf[T], p(19).asInstanceOf[U], p(20).asInstanceOf[V], p(21).asInstanceOf[W])
  }

}
