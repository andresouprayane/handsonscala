sealed trait Expr
case class BinOp(left: Expr, op: String, right: Expr) extends Expr
case class Literal(value: Int) extends Expr
case class Variable(name: String) extends Expr


def stringify(expr: Expr): String = expr match {
    case BinOp(left, op, right) => s"(${stringify(left)} $op ${stringify(right)})"
    case Literal(value) => value.toString
    case Variable(name) => name
}

def evaluate(expr: Expr, values: Map[String, Int]): Int = expr match {
    case BinOp(left, "+", right) => evaluate(left, values) + evaluate(right, values)
    case BinOp(left, "-", right) => evaluate(left, values) - evaluate(right, values)
    case BinOp(left, "*", right) => evaluate(left, values) * evaluate(right, values)
    case Literal(value) => value
    case Variable(name) => values(name)
}

val smallExpr = BinOp(
    Variable("x"),
    "+",
    Literal(1)
)

stringify(smallExpr)

val largeExpr = BinOp(
    BinOp(Variable("x"), "+", Literal(1)),
    "*",
    BinOp(Variable("y"), "-", Literal(1))
)

stringify(largeExpr)

evaluate(smallExpr, Map("x" -> 10))
evaluate(largeExpr, Map("x" -> 10, "y" -> 20))

// (1 + 1) => 2
val simpleExpr = BinOp(
    Literal(1),
    "+",
    Literal(1)
)

evaluate(simpleExpr,null)

// ((1 + 1) * x) => (2 * x)
val simpleExpr2 = BinOp(
    BinOp(Literal(1),
    "+",
    Literal(1)),"*",Variable("x")
)



// ((2 - 1) * x) => x

val simpleExpr3 = BinOp(
    BinOp(Literal(2),
    "-",
    Literal(1)),"*",Variable("x")
)


// (((1 + 1) * y) + ((1 - 1) * x)) => (2 * y)
val simpleExpr4 = BinOp(
    BinOp(
        BinOp(Literal(1),
        "+",
        Literal(1)),"*",Variable("y")),
    "+",
    BinOp(
        BinOp(Literal(1),
        "-",
        Literal(1)),"*",Variable("x"))
)

def simplify(expr: Expr): Expr = { val res = expr match {
    case BinOp(right : Literal, "+", left : Literal) => Literal(right.value + left.value)
    case BinOp(right : Literal, "-", left : Literal) => Literal(right.value - left.value)
    case BinOp(Literal(right), "*", Literal(left)) => Literal(left * right)    
    case BinOp(right : Literal, "*", left : Expr) if right == Literal(0) => Literal(0)
    case BinOp(right : Literal, "*", left : Expr) if left == Literal(0) => Literal(0)    
    case BinOp(right : Literal, "*", left : Expr) if right == Literal(1) => simplify(left)
    case BinOp(right : Literal, "*", left : Expr) if left == Literal(1) => simplify(right)    

    case BinOp(left, "+", right) => BinOp(simplify(left), "+", simplify(right))
    case BinOp(left, "-", right) => BinOp(simplify(left), "-", simplify(right))
    case BinOp(left, "*", right) => BinOp(simplify(left), "*", simplify(right))
    case Literal(value) => Literal(value)
    case Variable(name) => Variable(name)   
    }
    // We may need to re-simplify an expression multiple times in order to achieve
    // all the simplifications we want; only stop re-simplifying it if it stops changing
    if (res == expr) res
    else simplify(res)
}

simplify(simpleExpr4)








































