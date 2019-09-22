package src.kalelzar.placeholderengine.common.math

/**
  * Container object for implicit classes containing useful methods for mathematical operations with numbers
  */
object MathImplicits {

  /**
    *
    * Contains useful methods for mathematical operations with whole numbers
    *
    * @param self a whole number
    * @tparam A the type of whole number
    */
  implicit class IntegralOps[A: Integral](self: A) {

    import Integral.Implicits._

    /**
      * maps a number belonging to one numerical range to a number belonging to another numerical range
      *
      * @unit
      * @param from       start of first range
      * @param to         end of first range
      * @param scaledFrom start of second range
      * @param scaledTo   end of second range
      * @return the mapped number
      */
    def map(from: A, to: A, scaledFrom: A, scaledTo: A): Long = {
      Math.round((self - from).toDouble / (to - from).toDouble * (scaledTo - scaledFrom).toDouble + scaledFrom.toDouble)
    }


    /**
      * Raises this number to the power of 2
      *
      * @unit
      * @return the square of the number
      */
    def squared: Long = Math.pow(self.toDouble, 2).toLong

    /**
      * Returns this instance
      *
      * @return this
      */
    def asOp: IntegralOps[A] = this
  }

  /**
    *
    * Contains useful methods for mathematical operations with fractional numbers
    *
    * @param self a fractional number
    * @tparam A the type of fractional number
    */
  implicit class FractionalOps[A: Fractional](self: A) {

    import Fractional.Implicits._

    /**
      * maps a number belonging to one numerical range to a number belonging to another numerical range
      *
      * @unit
      * @param from       start of first range
      * @param to         end of first range
      * @param scaledFrom start of second range
      * @param scaledTo   end of second range
      * @return the mapped number
      */
    def map(from: A, to: A, scaledFrom: A, scaledTo: A): A = {
      (self - from) / (to - from) * (scaledTo - scaledFrom) + scaledFrom
    }

    /**
      * Raises this number to the power of 2
      *
      * @unit
      * @return the square of the number
      */
    def squared: Float = Math.pow(self.toDouble, 2).toFloat

    /**
      * Returns this instance
      *
      * @return this
      */
    def asOp: FractionalOps[A] = this
  }

}
