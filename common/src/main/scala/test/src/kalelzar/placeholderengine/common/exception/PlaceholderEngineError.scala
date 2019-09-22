package test.src.kalelzar.placeholderengine.common.exception

import src.kalelzar.placeholderengine.common.exception.PlaceholderEngineError
import src.kalelzar.placeholderengine.test.PlaceholderEngineFailTest

class PlaceholderEngineError_throwTest extends PlaceholderEngineFailTest {
  override def test(any: Seq[Any]): Unit = {
    throw PlaceholderEngineError("Test Error")
  }

  override def arguments: Seq[Any] = Seq.empty[Any]
}
