//import expression.show
//trait Expr {
//  def eval(e: Expr): Int = e match {
//    case Number(n) => n
//    case Sum(e1, e2) => e1.eval + e2.eval
//  }
//}

trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

object expression {
  def show(e: Expr): String = e match {
    case Number(n) => n.toString
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
  }

  println(show(Sum(Number(1), Number(44))))
}

def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => List(x)
  case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
}

def isort(xs: List[Int]): List[Int] = xs match {
  case List() => List()
  case y :: ys => insert(y, isort(ys))
}

val test = List(2, 3, 1, 5, 4)
isort(test)
