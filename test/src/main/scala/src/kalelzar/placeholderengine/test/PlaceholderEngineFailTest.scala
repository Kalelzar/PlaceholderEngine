package src.kalelzar.placeholderengine.test


trait PlaceholderEngineFailTest extends PlaceholderEngineTest {
  override def launch(any: Seq[Any]): Unit = {
    try {
      test(any)
    } catch {
      case e: Exception =>
        return
    }
    throw new FailedTestException(s"Test $name didn't fail! It was supposed to.")
  }
}