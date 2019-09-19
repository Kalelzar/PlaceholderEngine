package src.kalelzar.placeholderengine.test


import org.clapper.classutil.ClassFinder

import scala.collection.mutable

object PlaceholderEngineTestUtility {

  private val tests = mutable.ListBuffer[PlaceholderEngineTest]()

  def register(): Unit = {
    val finder = ClassFinder()
    val classes = finder.getClasses
    val tests = ClassFinder.concreteSubclasses("src.kalelzar.placeholderengine.test.PlaceholderEngineTest", classes.iterator)
    tests.foreach {
      test =>
        try {
          val c = Class.forName(test.name)
          val inst = c.newInstance().asInstanceOf[PlaceholderEngineTest]
          registerTest(inst)
        } catch {
          case e: Exception =>
            e.printStackTrace()
        }
    }
  }

  def registerTest(test: PlaceholderEngineTest): Unit = {
    tests += test
  }

  def begin(): Unit = {
    val count = tests.length
    var passed = 0f
    var index = 0
    Console.out.println("Beginning tests.")
    tests.foreach {
      x =>
        index += 1
        Console.out.println(s"[$index/$count] Running test ${x.name}.")
        val (success, errorMsg) = runTest(x)
        if (!success) {
          Console.err.println(s"Test ${x.name} failed. $errorMsg")
        } else {
          Console.out.println(s"Test ${x.name} passed.")
          passed += 1
        }
    }
    Console.out.println(s"\n${passed.toInt}/$count [${passed / count * 100}%] passed.")
  }

  def runTest(test: PlaceholderEngineTest): (Boolean, String) = {
    try {
      test.launch(test.arguments)
    } catch {
      case e: FailedTestException => return (false, e.getMessage)
      case e: Exception => return (false, s"Test failed with exception. ${e.getClass.getSimpleName}: ${e.getMessage}")
    }
    (true, "")
  }

}