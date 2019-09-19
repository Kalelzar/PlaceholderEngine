package src.kalelzar.placeholderengine.test


object PlaceholderEngineTestFunctions {

  def assertTrue(value: Boolean, msg: String = "Value is not true!"): Unit = {
    if (!value) except(msg)
  }

  def assertFalse(value: Boolean, msg: String = "Value is not false!"): Unit = {
    if (value) except(msg)
  }

  def assertEqual[A](a: A, b: A, msg: String = "Values are not equal!"): Unit = {
    if (!(a equals b)) except(msg + s" Expected $b but got $a")
  }

  def except(msg: String): Unit = throw new FailedTestException(msg)

  def assertNotEqual[A](a: A, b: A, msg: String = "Values are equal!"): Unit = {
    if (a equals b) except(msg)
  }

  def assertEqual[A](vals: Seq[A], msg: String): Unit = {
    if (!vals.forall(_ equals vals.head)) except(msg)
  }

  def assertNotEqual[A](vals: Seq[A], msg: String): Unit = {
    if (vals.forall(_ equals vals.head)) except(msg)
  }


}

class FailedTestException(msg: String) extends Exception(msg)