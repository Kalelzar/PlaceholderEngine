package test.src.kalelzar.placeholderengine.io.parse.format

import src.kalelzar.placeholderengine.io.parse.Token
import src.kalelzar.placeholderengine.io.parse.format._
import src.kalelzar.placeholderengine.test.PlaceholderEngineTest
import src.kalelzar.placeholderengine.test.PlaceholderEngineTestFunctions._

import scala.collection.mutable

class IniScanner_checkTest extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(String, Token)]].foreach {
      case (string, token) =>
        val scanner = new IniScanner
        scanner.setText(string)
        val res = scanner.check(scanner.next)
        assertEqual(res, token, "Tokens don't match.")
    }
  }

  override def arguments: Seq[Any] = {
    val list = mutable.ListBuffer[(String, Token)]()

    list ++= Seq(
      (" ?", Token(IDENTIFIER(), "?", 1, "?")),
      (" /", Token(IDENTIFIER(), "/", 1, "/")),
      (" =\"hello\"", Token(EQUALS(), "=", 1, "=")),
      (" \"hello\"", Token(IDENTIFIER(), "\"hello\"", 1, "\"hello\"")),
      (" [why]", Token(LEFT_BRACKET(), "[", 1, "[")),
      ("\n]nope", Token(RIGHT_BRACKET(), "]", 2, "]")),
      (";ignore;me", Token(IDENTIFIER(), "me", 1, "me"))
    )

    list.toSeq
  }
}

class IniScanner_identifierTest extends PlaceholderEngineTest {
  override def test(any: Seq[Any]): Unit = {
    any.asInstanceOf[Seq[(String, String)]].foreach {
      case (string, lexeme) =>
        val scanner = new IniScanner
        scanner.setText(string)
        val res = scanner.identifier(scanner.next)
        assertEqual(res.lexeme, lexeme, "Strings don't match.")
    }
  }

  override def arguments: Seq[Any] = {
    val list = mutable.ListBuffer[(String, String)]()

    list ++= Seq(
      ("hello", "hello"),
      ("a joke", "a joke"),
      ("whyIs1<2", "whyIs1<2"),
      ("WhyIs2>1", "WhyIs2>1"),
      ("Howdy=Hello, ", "Howdy"),
      ("no\nline", "no"),
      ("when1/2> you don't know math", "when1/2> you don't know math"),
      ("why;exist", "why"),
      ("no[life], ", "no"),
      ("no more]", "no more")
    )

    list.toSeq
  }
}
