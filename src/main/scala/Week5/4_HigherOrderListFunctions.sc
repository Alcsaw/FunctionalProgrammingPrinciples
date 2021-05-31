/*
Consider a function to square each element of
a list and return the result. Complete the two
following equivalent definitions of squareList.
 */
def squareList(xs: List[Int]): List[Int] =
  xs match {
    case Nil => Nil
    case y :: ys => y * y :: squareList(ys)
  }

val nums = List(1, 2, 3)
squareList(nums)

def squareList(xs: List[Int]): List[Int] =
  xs map (x => x * x)

squareList(nums)

def positiveElems(xs: List[Int]): List[Int] = xs match {
  case Nil => xs
  case y :: ys =>
    if (y > 0) y :: positiveElems(ys)
    else positiveElems(ys)
}

val nums2 = List(-2, 1, 0, 3)
positiveElems(nums2)

def positiveElems(xs: List[Int]): List[Int] =
  xs filter(x => x > 0)

positiveElems(nums2)

//Higher-Order functions examples:
nums2 filter (x => x > 0)
nums2 filterNot (x => x > 0)
nums2 partition (x => x > 0)

nums takeWhile(x => x > 0)
nums2 takeWhile(x => x > 0)
1 :: nums2 takeWhile(x => x > 0)
nums2 dropWhile (x => x > 0)
nums2 dropWhile (x => x < 0)
nums span(x => x > 0)

/*
Exercise:
Write a function pack that packs consecutive
duplicates of list elements into sublists.
For instance,
`pack(List("a", "a", "a", "b", "c", "c", "a"))`
should give
`List(List("a", "a", "a"), List("b"), List("c", "c"), List("a"))`
 */
def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (first, rest) = xs span (y => y == x)
    first :: pack(rest)
}

pack(List("a", "a", "a", "b", "c", "c", "a"))

/*
Exercise:
Using pack, write a function `encode` that
produces the run-length encoding of a list.
The idea is to encode n consecutive duplicates
of an element x as a pair (x, n). For instance,
`encode(List("a", "a", "a", "b", "c", "c", "a"))`
should give
`List(("a", 3), ("b", 1), ("c", 2), ("a", 1))`
 */
def encode[T](xs: List[T]): List[(T, Int)] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    pack(xs).map(ys => (ys.head, ys.length))
}

encode(List("a", "a", "a", "b", "c", "c", "a"))
