package src.kalelzar.placeholderengine.common.math

import MathImplicits._

object Mathf {

  def acos(alpha: Float): Float = Math.acos(alpha).toFloat

  def cosTForAngle(a: Float, b: Float, c: Float): Float = {
    acos((a.squared + b.squared - c.squared)/(2*a*b))
  }

}
