package src.kalelzar.placeholderengine.test


trait PlaceholderEngineTest {

  def name: String = "[" + this.getClass
    .getSimpleName
    .replace("Test", "")
    .replaceAll("_", " ")
    .mkString
    .trim + "]"

  def launch(): Unit = launch(Seq.empty[Nothing])

  def launch(any: Seq[Any]): Unit = test(any)

  def test(any: Seq[Any]): Unit

  def arguments: Seq[Any]


}