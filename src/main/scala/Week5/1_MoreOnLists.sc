import scala.annotation.tailrec

@tailrec
def last[T](xs: List[T]): T = xs match {
  case List() => throw new Error("last of empty list")
  case List(x) => x
  case y :: ys => last(ys)
}

def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("init of empty list")
  case List(x) => List()
  case y :: ys => List(y) ::: init(ys)
}

val list = List(1, 2, 3)
val list2 = List(4, 5)
last(list)
init(list)

list ::: list2
// concatenation is the same as calling
// the prepend method in the right-hand list
list2.:::(list)

def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List() => ys
  case z :: zs => z :: concat(zs, ys)
}

concat(list, list2)

def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => xs
  case y :: ys => reverse(ys) ++ List(y)
}

reverse(list)

def removeAt[T](n: Int, xs: List[T]): List[T] = (xs take n) ::: (xs drop n + 1)

removeAt(1, List('a', 'b', 'c', 'd')) // List(a, c, d)

def flatten(xs: List[Any]): List[Any] = xs match {
  case List() => Nil
  case (z :: zs) :: ys => z :: flatten(zs) ::: flatten(ys)
  case y :: ys => y :: flatten(ys)
}

flatten(List(List(1, 1), 2, List(3, List(5, 8)))) // List[Any] = List(1, 1, 2, 3, 5, 8)
