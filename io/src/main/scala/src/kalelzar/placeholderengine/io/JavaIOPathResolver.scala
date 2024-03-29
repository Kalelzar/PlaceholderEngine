package src.kalelzar.placeholderengine.io

import java.io.{BufferedReader, BufferedWriter, File, FileReader, FileWriter}

class JavaIOPathResolver extends PathResolver {

  /**
    * @inheritdoc
    * Resolved into a java.io.File
    * @param path the path to resolve
    * @return the resource
    */
  override def resolve(path: String): Resource[File] = new JavaIOResource(path)
}

/**
  * A resource relying on the implementation of java.io.File
  * @param _path the path of the file
  */
class JavaIOResource(_path: String) extends Resource[File] {
  path = _path
  source = new File(path)
  if (!source.exists()) {
    source.createNewFile()
  }

  /**
    * @inheritdoc
    * @param data the data to write
    */
  override def write(data: String): Unit = {
    if (source.canWrite) {
      val writer = new BufferedWriter(new FileWriter(source, false))

      writer.write(data)

      writer.flush()
      writer.close()
    } else {
      Console.err.println(s"Cannot write to $path.")
    }
  }

  /**
    * @inheritdoc
    * @param data the data to write
    */
  override def append(data: String): Unit = {
    if (source.canWrite) {
      val writer = new BufferedWriter(new FileWriter(source, true))

      writer.write(data)

      writer.flush()
      writer.close()
    } else {
      Console.err.println(s"Cannot append to $path.")
    }
  }

  /**
    * @inheritdoc
    * @return the content
    */
  override def read: String = {
    if (source.canRead) {
      val reader = new BufferedReader(new FileReader(source))
      val ss = new StringBuilder
      reader.lines().forEach(x => ss.append(x + "\n"))
      reader.close()
      ss.mkString
    } else {
      Console.err.println(s"Cannot read from $path.")
      ""
    }
  }
}
