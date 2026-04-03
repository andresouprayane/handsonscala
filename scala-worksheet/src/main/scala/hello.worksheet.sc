println("Hello, world!")
   
val x = 1
x + x

1 + 2 * 3 == 7

"hello world".substring(0, 5) == "hello"

class Foo(x: Int) {
  def printMsg(msg: String) = {
    println(msg + x)
  }
}

val f = new Foo(1)

f.printMsg("hello")

// Traits

trait Point{ def hypotenuse: Double }

class Point2D(x: Double, y: Double) extends Point{
  def hypotenuse = math.sqrt(x * x + y * y)
}

class Point3D(x: Double, y: Double, z: Double) extends Point{
  def hypotenuse = math.sqrt(x * x + y * y + z * z)
}

val points: Array[Point] = Array(new Point2D(1, 2), new Point3D(4, 5, 6))

val results = for (p <- points) yield p.hypotenuse

results.toSeq == Seq(2.23606797749979, 8.774964387392123)


