//import Week3.{Cons, List, Nil}
import Week3._

import scala.annotation.tailrec

@tailrec
def nth[T](n: Int, list: List[T]): T = {
  if (list.isEmpty) throw new IndexOutOfBoundsException
  else if (n == 0) list.head
  else nth(n - 1, list.tail)
}

//val list = new Cons[Int](1, new Cons[Int](2, new Cons[Int](3, new Nil)))
val list = new Week3.Cons(1, new Week3.Cons(2, new Cons(3, new Nil)))

nth(2, list)
nth(-1, list)
