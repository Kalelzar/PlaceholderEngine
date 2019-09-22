package test.src.kalelzar.placeholderengine.common.math

import src.kalelzar.placeholderengine.common.math.MathImplicits._
import src.kalelzar.placeholderengine.test.PlaceholderEngineTest
import src.kalelzar.placeholderengine.test.PlaceholderEngineTestFunctions.assertEqual

//70 / 100 * 58 + 0

import scala.util.Random

class MathImplicits_IntegralOps_Int_map extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int)]].foreach {
      case (og, from, to, scaledFrom, scaledTo, res) =>
        val m = og.map(from, to, scaledFrom, scaledTo)
        assertEqual(m, res, s"Mappings don't match! (original=$og, f=$from, t=$to, sf=$scaledFrom, st=$scaledTo)")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val og = Random.between(0, 100)
      val from = 0
      val to = 100
      val scale = Random.between(0, 100).toFloat / 100f
      val scaledFrom = 0
      val scaledTo = (to * scale).toInt
      val res = Math.round((og - from).toDouble / (to - from).toDouble * (scaledTo - scaledFrom).toDouble + scaledFrom.toDouble).toInt

      (og, from, to, scaledFrom, scaledTo, res)
    }
  }
}

class MathImplicits_IntegralOps_Long_map extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Long, Long, Long, Long, Long, Long)]].foreach {
      case (og, from, to, scaledFrom, scaledTo, res) =>
        val m = og.map(from, to, scaledFrom, scaledTo)
        assertEqual(m, res, s"Mappings don't match! (original=$og, f=$from, t=$to, sf=$scaledFrom, st=$scaledTo)")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val og = Random.between(0L, 100L)
      val from = 0L
      val to = 100L
      val scale = Random.between(0, 100).toDouble / 100.0
      val scaledFrom = 0L
      val scaledTo = (to * scale).toLong
      val res = Math.round((og - from).toDouble / (to - from).toDouble * (scaledTo - scaledFrom).toDouble + scaledFrom.toDouble)

      (og, from, to, scaledFrom, scaledTo, res)
    }
  }
}

class MathImplicits_IntegralOps_Long_squared extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Long, Long)]].foreach {
      case (og, res) =>
        val m = og.squared
        assertEqual(m, res, s"Squares don't match!")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val og = Random.between(0L, 100L)
      val res = Math.pow(og, 2).toLong
      (og, res)
    }
  }
}

class MathImplicits_IntegralOps_Int_squared extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int)]].foreach {
      case (og, res) =>
        val m = og.squared
        assertEqual(m, res, s"Squares don't match!")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val og = Random.between(0, 100)
      val res = Math.pow(og, 2).toInt
      (og, res)
    }
  }
}

class MathImplicits_FractionalOps_Float_squared extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float)]].foreach {
      case (og, res) =>
        val m = og.squared
        assertEqual(m, res, s"Squares don't match!")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val og = Random.between(0f, 100f)
      val res = Math.pow(og, 2).toFloat
      (og, res)
    }
  }
}

class MathImplicits_FractionalOps_Double_squared extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Double, Double)]].foreach {
      case (og, res) =>
        val m = og.squared
        assertEqual(m, res, s"Squares don't match!")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val og = Random.between(0d, 100d)
      val res = Math.pow(og, 2).toFloat.toDouble
      (og, res)
    }
  }
}

class MathImplicits_FractionalOps_Double_map extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Double, Double, Double, Double, Double, Double)]].foreach {
      case (og, from, to, scaledFrom, scaledTo, res) =>
        val m = og.map(from, to, scaledFrom, scaledTo)
        assertEqual(m, res, s"Mappings don't match! (original=$og, f=$from, t=$to, sf=$scaledFrom, st=$scaledTo)")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val og = Random.between(0d, 100d)
      val from = 0d
      val to = 100d
      val scale = Random.between(0d, 100d) / 100d
      val scaledFrom = 0d
      val scaledTo = to * scale
      val res = (og - from) / (to - from) * (scaledTo - scaledFrom) + scaledFrom

      (og, from, to, scaledFrom, scaledTo, res)
    }
  }
}

class MathImplicits_FractionalOps_Float_map extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Float, Float, Float, Float, Float, Float)]].foreach {
      case (og, from, to, scaledFrom, scaledTo, res) =>
        val m = og.map(from, to, scaledFrom, scaledTo)
        assertEqual(m, res, s"Mappings don't match! (original=$og, f=$from, t=$to, sf=$scaledFrom, st=$scaledTo)")
    }
  }

  override def arguments: Seq[Any] = {
    (0 to 1000).map { _ =>
      val og = Random.between(0f, 100f)
      val from = 0f
      val to = 100f
      val scale = Random.between(0f, 100f) / 100f
      val scaledFrom = 0f
      val scaledTo = to * scale
      val res = (og - from) / (to - from) * (scaledTo - scaledFrom) + scaledFrom

      (og, from, to, scaledFrom, scaledTo, res)
    }
  }
}