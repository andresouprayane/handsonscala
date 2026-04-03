import java.nio.file.Files.newBufferedWriter
import java.nio.file.Files.newBufferedReader
import java.io.BufferedWriter
import java.io.BufferedReader


def withFileWriter(fileName: String)(func : BufferedWriter => Unit) = {
  val output = java.nio.file.Files.newBufferedWriter(java.nio.file.Paths.get(fileName))
  try func(output)
  finally output.close()
    
}

withFileWriter("File.txt") { writer => 
writer.write("Hello\n"); writer.write("World!")
}

def withFileReader[T](fileName: String)(handler: java.io.BufferedReader => T) = {
  val input = java.nio.file.Files.newBufferedReader(java.nio.file.Paths.get(fileName))
  try handler(input)
  finally input.close()
}



