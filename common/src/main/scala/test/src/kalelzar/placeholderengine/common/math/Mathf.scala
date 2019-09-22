package test.src.kalelzar.placeholderengine.common.math

import src.kalelzar.placeholderengine.common.math.MathImplicits._
import src.kalelzar.placeholderengine.common.math.Mathf
import src.kalelzar.placeholderengine.test.PlaceholderEngineTest
import src.kalelzar.placeholderengine.test.PlaceholderEngineTestFunctions.assertEqual

import scala.util.Random

class Mathf_acosTest extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float)]].foreach {
      case (in, expected) =>
        assertEqual(Mathf.acos(in), expected, s"Angles don't match!")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { x =>
      val cos = (x - 500f) / 500f
      (cos, Math.acos(cos).toFloat)
    }
  }
}

class Mathf_cosTForAngleTest extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float, Float, Float)]].foreach {
      case (a, b, c, expected) =>
        val angle = Mathf.cosTForAngle(a, b, c)
        if (angle != expected) {
          println(a, b, c)
        }
        assertEqual(angle, expected, s"Angles don't match!")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val a = Random.between(1f, 100f)
      val b = Random.between(1f, 100f)
      val angle = Random.between(0f, 314f) / 100
      val c = a.squared + b.squared - 2 * a * b * Math.cos(angle).toFloat
      if (a + b < c) (a, b, c, 0f)
      else {
        val res = (a.squared + b.squared - c.squared) / (2 * a * b)
        (a, b, c, Mathf.acos(res))
      }

    }
  }
}
