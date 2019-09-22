package test.src.kalelzar.placeholderengine.common.math

import src.kalelzar.placeholderengine.common.math.MathImplicits._
import src.kalelzar.placeholderengine.common.math.{Mathf, Vector2}
import src.kalelzar.placeholderengine.test.PlaceholderEngineTest
import src.kalelzar.placeholderengine.test.PlaceholderEngineTestFunctions._

import scala.util.Random

class Vector2_MultiplyTest extends PlaceholderEngineTest {

  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float, Float, Float, Float)]].foreach {
      case (x, y, scalar, nx, ny) =>
        val vector = Vector2(x, y)
        val expectedVector = Vector2(nx, ny)
        assertEqual(vector.multiply(scalar), expectedVector)
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val x = Random.between(0, 100).toFloat
      val y = Random.between(0, 100).toFloat
      val scalar = Random.between(0, 20).toFloat
      val nx = x * scalar
      val ny = y * scalar
      (x, y, scalar, nx, ny)
    }
  }

}

class Vector2_AddTest extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float, Float, Float, Float, Float)]].foreach {
      case (x, y, x2, y2, nx, ny) =>
        val vector = Vector2(x, y)
        val other = Vector2(x2, y2)
        val expectedVector = Vector2(nx, ny)
        assertEqual(vector.add(other), expectedVector)
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val x = Random.between(0, 100).toFloat
      val y = Random.between(0, 100).toFloat
      val x2 = Random.between(0, 100).toFloat
      val y2 = Random.between(0, 100).toFloat
      val nx = x + x2
      val ny = y + y2
      (x, y, x2, y2, nx, ny)
    }
  }

}

class Vector2_SubtractTest extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float, Float, Float, Float, Float)]].foreach {
      case (x, y, x2, y2, nx, ny) =>
        val vector = Vector2(x, y)
        val other = Vector2(x2, y2)
        val expectedVector = Vector2(nx, ny)
        assertEqual(vector.subtract(other), expectedVector)
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val x = Random.between(0, 100).toFloat
      val y = Random.between(0, 100).toFloat
      val x2 = Random.between(0, 100).toFloat
      val y2 = Random.between(0, 100).toFloat
      val nx = x - x2
      val ny = y - y2
      (x, y, x2, y2, nx, ny)
    }
  }

}

class Vector2_NormalTest extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float)]].foreach {
      case (x, y) =>
        if(x!=0 || y!=0) {
          val vector = Vector2(x, y)
          val normal = vector.normal

          assertEqual(normal.magnitude, 1, "Magnitude of normal vector is not 1!")

          if (y == 0) {
            assertTrue(Math.abs(normal.x * vector.magnitude - vector.x) <= 0.00001, s"Vector not scaled properly! Expected ${vector.x}, got ${normal.x * vector.magnitude}")
          } else {
            val const = x / y
            val nconst = normal.x / normal.y
            assertTrue(Math.abs(const - nconst) <= 0.00001, s"Vector not scaled properly! Expected ${const}, got ${nconst}")
          }
        }
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val x = Random.between(0, 100).toFloat
      val y = Random.between(0, 100).toFloat
      (x, y)
    }
  }

}

class Vector2_DivideTest extends PlaceholderEngineTest {

  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float, Float, Float, Float)]].foreach {
      case (x, y, scalar, nx, ny) =>
        val vector = Vector2(x, y)
        val expectedVector = Vector2(nx, ny)
        assertEqual(vector.divide(scalar), expectedVector)
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val x = Random.between(0, 100).toFloat
      val y = Random.between(0, 100).toFloat
      val scalar = Random.between(0, 20).toFloat
      var nx = x / scalar
      var ny = y / scalar
      if (scalar == 0) {
        nx = 0
        ny = 0
      }
      (x, y, scalar, nx, ny)
    }
  }

}

class Vector2_DotProductTest extends PlaceholderEngineTest {

  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float, Float, Float, Float)]].foreach {
      case (x, y, nx, ny, dp) =>
        val vector = Vector2(x, y)
        val other = Vector2(nx, ny)
        assertEqual(vector.dotProduct(other), dp, "Dot products don't match!")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val x = Random.between(0, 100).toFloat
      val y = Random.between(0, 100).toFloat
      val nx = Random.between(0, 100).toFloat
      val ny = Random.between(0, 100).toFloat
      val dp = x * nx + y * ny
      (x, y, nx, ny, dp)
    }
  }

}

class Vector2_AngleTest extends PlaceholderEngineTest {

  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float, Float, Float, Float)]].foreach {
      case (x, y, nx, ny, r) =>
        val vector = Vector2(x, y)
        val other = Vector2(nx, ny)
        val a = vector.angle(other)
        assertTrue(Math.abs(a - r) <= 0.000001, s"Angles don't match! Expected $r, got $a instead")
    }
  }

  def mag(x: Float, y: Float): Float = Math.sqrt(x*x + y*y).toFloat

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val x = Random.between(0, 100).toFloat
      val y = Random.between(0, 100).toFloat
      val nx = Random.between(0, 100).toFloat
      val ny = Random.between(0, 100).toFloat


      val a = Vector2(x, y)
      val b = Vector2(nx, ny)

      val r = if (a.distance(b) == 0) 0
      else if (a.magnitude == 0) {
        val n = b.normal
        Mathf.cosTForAngle(n.x, n.y, 1)
      } else if (b.magnitude == 0) {
        val n = a.normal
        Mathf.cosTForAngle(n.x, n.y, 1)
      } else Mathf.cosTForAngle(a.magnitude, b.magnitude, a.distance(b))

      (x, y, nx, ny, r)
    }
  }

}

class Vector2_DistanceTest extends PlaceholderEngineTest {

  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float, Float, Float, Float)]].foreach {
      case (x, y, nx, ny, dp) =>
        val vector = Vector2(x, y)
        val other = Vector2(nx, ny)
        assertEqual(vector.distance(other), dp, "Distances don't match!")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val x = Random.between(0, 100).toFloat
      val y = Random.between(0, 100).toFloat
      val nx = Random.between(0, 100).toFloat
      val ny = Random.between(0, 100).toFloat
      val dp = Math.sqrt(Math.pow(x - nx, 2) + Math.pow(y - ny, 2)).toFloat
      (x, y, nx, ny, dp)
    }
  }

}

class Vector2_LimitTest extends PlaceholderEngineTest {

  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float, Float, Float, Float)]].foreach {
      case (x, y, n, nx, ny) =>

        val vector = Vector2(x, y)
        val other = Vector2(nx, ny)
        val limited = vector.limit(n)
        assertEqual(limited, other, "Vectors don't match!")
        assertTrue(limited.magnitude <= n + 0.00001, s"Limited magnitude is greater than limit. Expected ${n}, got ${limited.magnitude} instead")
    }
  }

  def mag(x: Float, y: Float): Float = Math.sqrt(x*x + y*y).toFloat

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val x = Random.between(0, 100).toFloat
      val y = Random.between(0, 100).toFloat
      val z = Random.between(0, 20).toFloat
      val curMag = mag(x,y)
      if(curMag <= z) (x, y, z, x, y)
      else {
        if (y == 0f) (x, y, z, Math.signum(x)*Math.abs(z), 0F)
        else {
          val n = Vector2(x,y).normal
          if(x == 0) (x, y, z, 0f, Math.signum(y)*Math.abs(z))
          else {
            val a = (y - n.y) / (x - n.x)
            val b = y - a * x

            val a2 = a.squared
            val b2 = b.squared
            val mag2 = z.squared

            if (a2 * mag2 - b2 + mag2 < 0) {
              val x1 = (-a * b) / (a2 + 1)
              val y1 = x1 * a + b
              (x, y, z, x1, y1)
            } else {

              val v = Vector2(x, y)
              val x11 = (Math.sqrt(a2 * mag2 - b2 + mag2).toFloat - a * b) / (a2 + 1)
              val x12 = (-Math.sqrt(a2 * mag2 - b2 + mag2).toFloat - a * b) / (a2 + 1)
              val x1 = if (Vector2(x11, x11 * a + b).distance(v) < Vector2(x12, x12 * a + b).distance(v)) x11 else x12
              val y1 = x1 * a + b
              (x, y, z, x1, y1)
            }
          }
        }
      }
    }
  }

}