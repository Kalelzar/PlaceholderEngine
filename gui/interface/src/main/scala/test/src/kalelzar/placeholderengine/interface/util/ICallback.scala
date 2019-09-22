package test.src.kalelzar.placeholderengine.interface.util

import src.kalelzar.placeholderengine.interface.util.Callback._
import src.kalelzar.placeholderengine.test.PlaceholderEngineTest
import src.kalelzar.placeholderengine.test.PlaceholderEngineTestFunctions._

class Callback_F0Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    assertEqual(f(), (f _).asCallback.apply())
  }

  def f(): Int = 0

  override def arguments: Seq[Any] = Seq.empty[Any]
}

class Callback_F1Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[Int]].foreach {
      a =>
        assertEqual(f(a), (f _).asCallback(a))
    }

  }

  def f(a: Int): Int = a

  override def arguments: Seq[Any] = 0 to 1000

}

class Callback_F2Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int)]].foreach {
      case (a, b) =>
        assertEqual(func(a, b), (func _).asCallback(a, b))
    }

  }

  def func(a: Int, b: Int): Int = a + b

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1)
  }
}

class Callback_F3Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int)]].foreach {
      case (a, b, c) =>
        assertEqual(func(a, b, c), (func _).asCallback(a, b, c))
    }

  }

  def func(a: Int, b: Int, c: Int): Int = a + b - c

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2)
  }
}

class Callback_F4Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int)]].foreach {
      case (a, b, c, d) =>
        assertEqual(func(a, b, c, d), (func _).asCallback(a, b, c, d))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int): Int = a - b + c - d

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3)
  }
}

class Callback_F5Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e) =>
        assertEqual(func(a, b, c, d, e), (func _).asCallback(a, b, c, d, e))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int): Int = a - b + c - d + e

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4)
  }
}

class Callback_F6Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f) =>
        assertEqual(func(a, b, c, d, e, f), (func _).asCallback(a, b, c, d, e, f))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int): Int = a + b - c + d - e + f

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5)
  }
}

class Callback_F7Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g) =>
        assertEqual(func(a, b, c, d, e, f, g), (func _).asCallback(a, b, c, d, e, f, g))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int): Int = a + b - c + d - e + f - g

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6)
  }
}

class Callback_F8Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h) =>
        assertEqual(func(a, b, c, d, e, f, g, h), (func _).asCallback(a, b, c, d, e, f, g, h))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int): Int = a - b + c - d + e - f + g - h

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7)
  }
}

class Callback_F9Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i), (func _).asCallback(a, b, c, d, e, f, g, h, i))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int): Int = a - b + c - d + e - f + g - h + i

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8)
  }
}

class Callback_F10Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j), (func _).asCallback(a, b, c, d, e, f, g, h, i, j))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int): Int = a + b - c + d - e + f - g + h - i + j

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9)
  }
}

class Callback_F11Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j, k) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j, k), (func _).asCallback(a, b, c, d, e, f, g, h, i, j, k))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int): Int = a + b - c + d - e + f - g + h - i + j - k

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9, i + 10)
  }
}

class Callback_F12Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j, k, l) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j, k, l), (func _).asCallback(a, b, c, d, e, f, g, h, i, j, k, l))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int): Int = a - b + c - d + e - f + g - h + i - j + k - l

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9, i + 10, i + 11)
  }
}

class Callback_F13Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j, k, l, m) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j, k, l, m), (func _).asCallback(a, b, c, d, e, f, g, h, i, j, k, l, m))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int): Int = a - b + c - d + e - f + g - h + i - j + k - l + m

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9, i + 10, i + 11, i + 12)
  }
}

class Callback_F14Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j, k, l, m, n) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j, k, l, m, n), (func _).asCallback(a, b, c, d, e, f, g, h, i, j, k, l, m, n))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int): Int = a + b - c + d - e + f - g + h - i + j - k + l - m + n

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9, i + 10, i + 11, i + 12, i + 13)
  }
}

class Callback_F15Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o), (func _).asCallback(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int): Int = a + b - c + d - e + f - g + h - i + j - k + l - m + n - o

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9, i + 10, i + 11, i + 12, i + 13, i + 14)
  }
}

class Callback_F16Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p), (func _).asCallback(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int): Int = a - b + c - d + e - f + g - h + i - j + k - l + m - n + o - p

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9, i + 10, i + 11, i + 12, i + 13, i + 14, i + 15)
  }
}

class Callback_F17Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q), (func _).asCallback(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int): Int = a - b + c - d + e - f + g - h + i - j + k - l + m - n + o - p + q

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9, i + 10, i + 11, i + 12, i + 13, i + 14, i + 15, i + 16)
  }
}

class Callback_F18Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r), (func _).asCallback(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int, r: Int): Int = a + b - c + d - e + f - g + h - i + j - k + l - m + n - o + p - q + r

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9, i + 10, i + 11, i + 12, i + 13, i + 14, i + 15, i + 16, i + 17)
  }
}

class Callback_F19Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s), (func _).asCallback(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int, r: Int, s: Int): Int = a + b - c + d - e + f - g + h - i + j - k + l - m + n - o + p - q + r - s

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9, i + 10, i + 11, i + 12, i + 13, i + 14, i + 15, i + 16, i + 17, i + 18)
  }
}

class Callback_F20Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t), (func _).asCallback(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int, r: Int, s: Int, t: Int): Int = a - b + c - d + e - f + g - h + i - j + k - l + m - n + o - p + q - r + s - t

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9, i + 10, i + 11, i + 12, i + 13, i + 14, i + 15, i + 16, i + 17, i + 18, i + 19)
  }
}

class Callback_F21Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u), (func _).asCallback(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int, r: Int, s: Int, t: Int, u: Int): Int = a - b + c - d + e - f + g - h + i - j + k - l + m - n + o - p + q - r + s - t + u

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9, i + 10, i + 11, i + 12, i + 13, i + 14, i + 15, i + 16, i + 17, i + 18, i + 19, i + 20)
  }
}

class Callback_F22Test extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)]].foreach {
      case (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v) =>
        assertEqual(func(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v), (func _).asCallback(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v))
    }

  }

  def func(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int, r: Int, s: Int, t: Int, u: Int, v: Int): Int = a + b - c + d - e + f - g + h - i + j - k + l - m + n - o + p - q + r - s + t - u + v

  override def arguments: Seq[Any] = (0 to 1000).map {
    i =>
      (i + 0, i + 1, i + 2, i + 3, i + 4, i + 5, i + 6, i + 7, i + 8, i + 9, i + 10, i + 11, i + 12, i + 13, i + 14, i + 15, i + 16, i + 17, i + 18, i + 19, i + 20, i + 21)
  }
}


