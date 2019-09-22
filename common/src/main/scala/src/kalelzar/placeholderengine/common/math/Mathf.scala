package src.kalelzar.placeholderengine.common.math

import src.kalelzar.placeholderengine.common.math.MathImplicits._

object Mathf {

  def acos(alpha: Float): Float = Math.acos(alpha).toFloat

  def cosTForAngle(a: Float, b: Float, c: Float): Float = {
    if (a == 0 || b == 0) 0f
    else if (Math.abs(a.squared + b.squared - c.squared) > 2 * a * b) 0
    else acos((a.squared + b.squared - c.squared) / (2 * a * b))
  }

}
