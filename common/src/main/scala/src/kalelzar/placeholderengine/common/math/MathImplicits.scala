package src.kalelzar.placeholderengine.common.math

object MathImplicits {

  implicit class IntegralOps[A: Integral](self: A) {

    import Integral.Implicits._

    def map(from: A, to: A, scaledFrom: A, scaledTo: A): A = {
      (self - from) / (to - from) * (scaledTo - scaledFrom) + scaledFrom
    }

    def squared: Float = Math.pow(self.toDouble, 2).toFloat

    def asOp: IntegralOps[A] = this
  }

  implicit class FractionalOps[A: Fractional](self: A) {

    import Fractional.Implicits._

    def map(from: A, to: A, scaledFrom: A, scaledTo: A): A = {
      (self - from) / (to - from) * (scaledTo - scaledFrom) + scaledFrom
    }

    def squared: Float = Math.pow(self.toDouble, 2).toFloat

    def asOp: FractionalOps[A] = this
  }

}
