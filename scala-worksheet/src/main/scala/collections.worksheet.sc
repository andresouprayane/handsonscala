def stdDev(a: Array[Double]): Double = {
    val mean = a.sum / a.length
    val squareErrors = a.map(x => x - mean).map(x => x * x)
    math.sqrt(squareErrors.sum / a.length)
}

val a = Array(1,2,3,4)

def isValidSudoku(grid: Array[Array[Int]]): Boolean = {
    !Range(0, 9).exists{i =>
    val row = Range(0, 9).map(grid(i)(_))
    val col = Range(0, 9).map(grid(_)(i))
    val square = Range(0, 9).map(j => grid((i % 3) * 3 + j % 3)((i / 3) * 3 + j / 3))
    row.distinct.length != row.length ||
    col.distinct.length != col.length ||
    square.distinct.length != square.length
    }
}


val grid = Array(
Array(5, 3, 4, 6, 7, 8, 9, 1, 2),
Array(6, 7, 2, 1, 9, 5, 3, 4, 8),
Array(1, 9, 8, 3, 4, 2, 5, 6, 7),
Array(8, 5, 9, 7, 6, 1, 4, 2, 3),
Array(4, 2, 6, 8, 5, 3, 7, 9, 1),
Array(7, 1, 3, 9, 2, 4, 8, 5, 6),
Array(9, 6, 1, 5, 3, 7, 2, 8, 4),
Array(2, 8, 7, 4, 1, 9, 6, 3, 5),
Array(3, 4, 5, 2, 8, 6, 1, 7, 9)
)

val row = Range(0, 9).map(grid(1)(_)).filter(_ != 0)



row.distinct.length != row.length

grid(1).distinct.length

val square = Range(0, 9).map(j => grid((1 % 3) * 3 + j % 3)((1 / 3) * 3 + j / 3))

val grid_invalid = Array(
Array(3, 1, 6, 5, 7, 8, 4, 9, 2),
Array(5, 2, 9, 1, 3, 4, 7, 6, 8),
Array(4, 8, 7, 6, 2, 9, 5, 3, 1),
Array(2, 6, 3, 0, 1, 0, 0, 8, 0),
Array(9, 7, 4, 8, 6, 3, 0, 0, 5),
Array(8, 5, 1, 0, 9, 0, 6, 0, 0),
Array(1, 3, 0, 0, 0, 0, 2, 5, 0),
Array(0, 0, 0, 0, 0, 0, 0, 7, 4),
Array(0, 0, 5, 2, 0, 6, 3, 0, 0)
)


def isValidSudoku2(grid: Array[Array[Int]]): Boolean = {
    !Range(0, 9).exists{i =>
    val row = Range(0, 9).map(grid(i)(_)).filter(_ != 0)
    val col = Range(0, 9).map(grid(_)(i)).filter(_ != 0)
    val square = Range(0, 9).map(j => grid((i % 3) * 3 + j % 3)((i / 3) * 3 + j / 3)).filter(_ != 0)
    row.distinct.length != row.length ||
    col.distinct.length != col.length ||
    square.distinct.length != square.length
    }
}

isValidSudoku2(grid_invalid)


def renderSudoku(grid: Array[Array[Int]]) : Unit = {
    def printHorizontalSep() = {
        print("-")
    }

    def printVerticalSep() = {
        print("|")
    }

    val horizontalSeparation = " - "
    val horizontalMatriceSeparation = " + "
    val verticalSeparation = " | "

    var h = ""
    var i = 0
    for (grid_unit <- grid ) {
        if (i % 3 == 0) {
             h += "+" + ( horizontalSeparation * 7 + "+") * (grid(0).length / 3) + "\n"
        }
        i += 1
        //val h1 = Range(0,3).map(grid(0)(_)).mkString(" ") + verticalSeparation + Range(3,6).map(grid(0)(_)).mkString(" ") + verticalSeparation + Range(6,9).map(grid(0)(_)).mkString(" ")
        h += verticalSeparation + Range(0,3).map(grid_unit(_)).mkString("  ") + verticalSeparation + Range(3,6).map(grid_unit(_)).mkString("  ") + verticalSeparation + Range(6,9).map(grid_unit(_)).mkString("  ") + verticalSeparation + "\n"
    }

    print(h)
}

renderSudoku(grid)


def renderSudoku2(grid: Array[Array[Int]]) = {
  val rowSeparator = "\n+-------+-------+-------+\n"
  grid
    .map(row =>
      row
        .map(i => if(i == 0) " " else i.toString)
        .grouped(3)
        .map(_.mkString(" "))
        .mkString("| ", " | ", " |")
    )
    .grouped(3)
    .mkString(rowSeparator, rowSeparator, rowSeparator)
}

renderSudoku2(grid)


Array(3, 1, 6, 5, 7, 8, 4, 9, 2).grouped(3).map(_.mkString("_")).mkString("| ", " | ", " |")


//.map(_.mkString("_"))















