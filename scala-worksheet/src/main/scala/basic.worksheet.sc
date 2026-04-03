object flexibleFizzBuss {
    def flexibleFizzBuzz(func: String => Unit) = {
        for (i <- Range.inclusive(1, 100)) {
            func(
            if (i % 3 == 0 && i % 5 == 0) "FizzBuzz"
            else if (i % 3 == 0) "Fizz"
            else if (i % 5 == 0) "Buzz"
            else i.toString
            )
        }
    }
}

import flexibleFizzBuss._

val func2 = flexibleFizzBuzz(s => println(s))
//func2("1")

var i = 0

val output = new Array[String](100)

val func3 = flexibleFizzBuzz{s => 
    output(i) = s
    i += 1
    }




//func3("a")
//func3("b")

output






