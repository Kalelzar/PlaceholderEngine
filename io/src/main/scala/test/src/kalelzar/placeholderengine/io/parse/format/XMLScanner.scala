package test.src.kalelzar.placeholderengine.io.parse.format

import src.kalelzar.placeholderengine.io.parse.Token
import src.kalelzar.placeholderengine.io.parse.format._
import src.kalelzar.placeholderengine.test.PlaceholderEngineTest
import src.kalelzar.placeholderengine.test.PlaceholderEngineTestFunctions._

import scala.collection.mutable

class XMLScanner_checkTest extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(String, Token)]].foreach {
      case (string, token) =>
        val scanner = new XMLScanner
        scanner.setText(string)
        val res = scanner.check(scanner.next)
        assertEqual(res, token, "Tokens don't match.")
    }
  }

  override def arguments: Seq[Any] = {
    val list = mutable.ListBuffer[(String, Token)]()

    list ++= Seq(
      ("<go ", Token(LEFT_ARROW(), "<", 1, "<")),
      ("</ ", Token(CLOSING_LEFT_ARROW(), "</", 1, "</")),
      ("\n<", Token(LEFT_ARROW(), "<", 2, "<")),
      (" >", Token(RIGHT_ARROW(), ">", 1, ">")),
      (" />", Token(CLOSING_RIGHT_ARROW(), "/>", 1, "/>")),
      (" ?>", Token(QRIGHT_ARROW(), "?>", 1, "?>")),
      (" <?", Token(QLEFT_ARROW(), "<?", 1, "<?")),
      (" ?", Token(IDENTIFIER(), "?", 1, "?")),
      (" /", Token(IDENTIFIER(), "/", 1, "/")),
      (" =\"hello\"", Token(EQUALS(), "=", 1, "=")),
      (" =fd", Token(IDENTIFIER(), "=fd", 1, "=fd")),
      (" \"hello\"", Token(IDENTIFIER(), "hello", 1, "hello"))
    )

    list.toSeq
  }
}

class XMLScanner_identifierTest extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(String, String)]].foreach {
      case (string, lexeme) =>
        val scanner = new XMLScanner
        scanner.setText(string)
        val res = scanner.identifier(scanner.next)
        assertEqual(res.lexeme, lexeme, "Strings don't match.")
    }
  }

  override def arguments: Seq[Any] = {
    val list = mutable.ListBuffer[(String, String)]()

    list ++= Seq(
      ("hello", "hello"),
      ("a joke", "a"),
      ("whyIs1<2", "whyIs1"),
      ("WhyIs2>1", "WhyIs2"),
      ("Howdy=Hello, ", "Howdy"),
      ("no\nline", "no"),
      ("when1/2> you don't know math", "when1")
    )

    list.toSeq
  }
}
