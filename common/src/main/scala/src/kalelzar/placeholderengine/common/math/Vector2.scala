package src.kalelzar.placeholderengine.common.math

/**
  * An immutable representation of a two-dimensional vector
  *
  * @param x the x position
  * @param y the y position
  */
case class Vector2(x: Float, y: Float) {

  /**
    * Multiply this vector by a scalar
    *
    * @unit
    * @param scalar a floating point number
    * @return the new vector2
    */
  def multiply(scalar: Float): Vector2 = {
    Vector2(x * scalar, y * scalar)
  }

  /**
    * Divide this vector by a scalar
    * @unit
    * @param scalar a floating point number
    * @return the new vector2
    */
  def divide(scalar: Float): Vector2 = {
    if (scalar == 0) return Vector2.zero
    Vector2(x / scalar, y / scalar)
  }

  /**
    * The angle in radians between this vector and another vector
    *
    * @param other the other vector
    * @return the angle
    */
  def angle(other: Vector2): Float = {
    Math.acos(dotProduct(other) / (magnitude * other.magnitude)).toFloat
  }

  /**
    * The dot product with another vector
    *
    * @unit
    * @param other the other vector
    * @return the dot product
    */
  def dotProduct(other: Vector2): Float = {
    x * other.x + y * other.y
  }

  /**
    * The Euclidean distance between this vector and another
    *
    * @param other the other vector
    * @return the distance
    */
  def distance(other: Vector2): Float = {
    Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2)).toFloat
  }

  /**
    * Add another vector to this one
    *
    * @unit
    * @param other the other vector
    * @return the sum of the two vectors
    */
  def add(other: Vector2): Vector2 = {
    Vector2(x + other.x, y + other.y)
  }

  /**
    * Subtract another vector from this one
    *
    * @unit
    * @param other the other vector
    * @return the difference between the vectors
    */
  def subtract(other: Vector2): Vector2 = {
    Vector2(x - other.x, y - other.y)
  }

  /**
    * Normalizes the vector so that it has a magnitude of 1
    *
    * @unit
    * @return the normalized vector
    */
  def normal: Vector2 = {
    if (magnitude != 0) {
      val res = divide(magnitude)
      if (res.magnitude == 1) res
      else res.normal
    }
    else Vector2.zero
  }

  /**
    * Limits the magnitude of the vector to the upper bound provided
    * If it is smaller it just returns this vector
    * Otherwise it scales the vector down
    *
    * @param mag the upper bound for magnitude
    * @return the limited vector
    */
  def limit(mag: Float): Vector2 = {
    if (magnitude > mag) {
      if (y == 0) return Vector2(Math.abs(mag), 0)
      val const = x / y
      val y1 = Math.abs(mag) * Math.sqrt(1 / (1 + const)).toFloat
      val x1 = y1 * const
      Vector2(x1, y1)
    } else this
  }

  /**
    * The magnitude of the vector
    *
    * @return the magnitude
    */
  def magnitude: Float = {
    Math.sqrt(x * x + y * y).toFloat
  }

}

object Vector2 {
  /**
    * A vector equal to (0, 0)
    */
  val zero = Vector2(0, 0)
  val up = Vector2(0, -1)
  val down = Vector2(0, 1)
  val left = Vector2(-1, 0)
  val right = Vector2(1, 0)
}
