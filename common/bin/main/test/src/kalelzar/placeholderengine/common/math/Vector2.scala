package test.src.kalelzar.placeholderengine.common.math

import src.kalelzar.placeholderengine.common.math.Vector2
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
    (0 to 100).map { _ =>
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
    (0 to 100).map { _ =>
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
    (0 to 100).map { _ =>
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

  override def arguments: Seq[Any] = {
    (0 to 100).map { _ =>
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
    (0 to 100).map { _ =>
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
    (0 to 100).map { _ =>
      val x = Random.between(0, 100).toFloat
      val y = Random.between(0, 100).toFloat
      val nx = Random.between(0, 100).toFloat
      val ny = Random.between(0, 100).toFloat
      val dp = x * nx + y * ny
      (x, y, nx, ny, dp)
    }
  }

}

//TODO: Actually check angle here
class Vector2_AngleTest extends PlaceholderEngineTest {

  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float, Float, Float, Float)]].foreach {
      case (x, y, nx, ny, dp) =>
        val vector = Vector2(x, y)
        val other = Vector2(nx, ny)
        assertEqual(vector.dotProduct(other), dp, "Dot products don't match!")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 100).map { _ =>
      val x = Random.between(0, 100).toFloat
      val y = Random.between(0, 100).toFloat
      val nx = Random.between(0, 100).toFloat
      val ny = Random.between(0, 100).toFloat
      val dp = x * nx + y * ny
      (x, y, nx, ny, dp)
    }
  }

}