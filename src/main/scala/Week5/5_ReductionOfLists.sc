def sum(xs: List[Int]): Int = xs match {
  case Nil => 0
  case y :: ys => y + sum(ys)
}

val nums = List(-2, 1, 2, 3)

sum(nums)

/*
This pattern can be abstracted out using
the generic method `reduceLeft`, which
inserts a given binary operator between
adjacent elements of a list.
*/
def sum(xs: List[Int]): Int =
  (0 :: xs) reduceLeft ((x, y) => x + y)

def product(xs: List[Int]): Int =
  (1 :: xs) reduceLeft ((x, y) => x * y)

sum(nums)
product(nums)

/*
A shorter way to write functions:
Instead of
 `((x, y) => x + y)`,
one can also write shorter:
 `(_ * _)`
Every _ represents a new parameter,
going from left to right.
The parameters are defined at the
next outer pair of parentheses
(or the whole expression if there
are no enclosing parentheses).
So, `sum`and `product` can also be
expressed like this:
 */
def sum(xs: List[Int]): Int =
  (0 :: xs) reduceLeft (_ + _)

def product(xs: List[Int]): Int =
  (1 :: xs) reduceLeft (_ * _)

sum(nums)
product(nums)

/* FoldLeft:
The function reduceLeft is defined
in terms of a more general function,
`foldLeft´.
`foldLeft is like reduceLeft, but it takes
an accumulator, z, as an additional
parameter, which is returned when
foldLeft is called on an empty list.
So, sum and product can also be defined
as follows:
 */
def sum(xs: List[Int]): Int =
  (xs foldLeft 0) (_ + _)

def product(xs: List[Int]): Int =
  (xs foldLeft 1) (_ * _)

sum(nums)
product(nums)

/*
Applications of foldLeft and reduceLeft unfold
on trees that lean to the left.
They have two dual functions, foldRight and reduceRight,
which produce trees which lean to the right.

While foldLeft would start the first operation
with the zero element operated with the first
element of the list (operated with the next and so on),
foldRight starts with the operation between the first
element of the list with the second, then operated with
the third and so on, until it gets to the last element
and combines it to the zero value.

For operators that are associative or commutative,
foldLeft and foldRight are equivalent, event though
there may be a difference in efficiency. But sometimes
only one of the two operators is appropriate.
 */

def concat[T](xs: List[T], ys: List[T]): List[T] =
  (xs foldRight ys) (_ :: _)

val string = "This is the difference between"
val string2 = "foldLeft and foldRight"
val strList = List(string)
val strList2 = List(string2)
concat(strList, strList2)

//def concat[T](xs: List[T], ys: List[T]): List[T] =
//  (xs foldLeft ys) (_ :: _)
// generates the following error:
//value :: is not a member of type parameter T
//(xs foldLeft ys) (_ :: _)

/*
Complete the following definitions of the
basic functions map and length on lists,
such that their implementation uses foldRight:
*/
def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]())((y, ys) => f(y) :: ys)

def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0)((y, ys) => 1 + ys)

nums
mapFun(nums, (x: Int) => x * 2)
lengthFun(nums)

/*
The following are the implementations of
foldLeft and foldRight in the List class
 */
abstract class List[T] {
  // ...
  def reduceLeft(op: (T, T) => T): T = this match {
    case Nil => throw new Error("Nil.reduceLeft")
    case x :: xs => (xs foldLeft x)(op)
  }

  def foldLeft[U](z: U)(op: (U, T) => U): U = this match {
    case Nil => z
    case x :: xs => (xs foldLeft op(z, x))(op)
  }

  def reduceRight(op: (T, T) => T): T = this match {
    case Nil => throw new Error("Nil.reduceRight")
    case x :: Nil => x
    case x :: xs => op(x, xs.reduceRight(op))
  }

  def foldRight[U](z: U)(op: (T, U) => U): U = this match {
    case Nil => z
    case x :: xs => op(x, (xs foldRight z)(op))
  }
}
