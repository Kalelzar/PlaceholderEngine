package src.kalelzar.placeholderengine.common.math

object Implicits {

  implicit class NumericalOps[A: Integral](self: A) {

    import Integral.Implicits._

    def map(from: A, to: A, scaledFrom: A, scaledTo: A): A = {
      (self - from) / (to - from) * (scaledTo - scaledFrom) + scaledFrom
    }

  }

}
