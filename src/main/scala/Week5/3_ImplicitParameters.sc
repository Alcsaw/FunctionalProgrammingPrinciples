/*
Generalizing merge sort to any type of list.
Only using type parameter is not enough,
since a type does not necessarily have a
comparison function defined. So we can
parameterize merge with the necessary
comparison function. We would have a
polymorphic function `msort` with a type
parameter T and an additional parameter that
is a function "less than"
 */
def mSort2[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
  val n = xs.length/2

  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, zs) => zs
        case (zs, Nil) => zs
        case (w::ws, z::zs) =>
          if (lt(w, z)) w :: merge(ws, ys)
          else z :: merge(zs, xs)
      }

    val (fst, snd) = xs splitAt n
    merge(mSort2(fst)(lt), mSort2(snd)(lt))
  }
}

val nums = List(2, 3, 1, 6, 4, 0, -2)

val less_than = (x: Int, y: Int) => x < y
mSort2(nums)(less_than)
/*
The types of the two function values are not
necessary. We can leave them out and the Scala
compiler will infer them. So we could also write
something like the below, and that would give us
the same list. The reason why that works is that
the Scala compiler is able to figure out that X
and Y need to have type Int by simply analyzing
the call of `mSort2` of `nums`, because `nums`
is a list of Int. It will therefore know that
the type parameter of mSort must be Int and
that will determine, in turn, the types of the
function parameters.
 */
mSort2(nums)((x, y) => x < y)

val fruits = List("apple", "pineapple", "orange", "banana")

mSort2(fruits)((x: String, y: String) => x.compareTo(y) < 0)
mSort2(fruits)((x, y) => x.compareTo(y) < 0)

/*
There is already a class in the standard library
that represents orderings:
`scala.math.Ordering[T]` provides ways to compare
elements of type T. So, instead of parameterizing
with the lt operation directly, we could
parameterize with Ordering:
 */
def mSort3[T](xs: List[T])(ord: Ordering[T]): List[T] = {
  val n = xs.length/2

  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, zs) => zs
        case (zs, Nil) => zs
        case (w::ws, z::zs) =>
          if (ord.lt(w, z)) w :: merge(ws, ys)
          else z :: merge(zs, xs)
      }

    val (fst, snd) = xs splitAt n
    merge(mSort3(fst)(ord), mSort3(snd)(ord))
  }
}

mSort3(nums)(Ordering.Int)
mSort3(fruits)(Ordering.String)

/*
We can avoid passing lt or ord values by
making ord an implicit parameter.
*/
def mSort4[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
  val n = xs.length/2

  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, zs) => zs
        case (zs, Nil) => zs
        case (w::ws, z::zs) =>
          if (ord.lt(w, z)) w :: merge(ws, ys)
          else z :: merge(zs, xs)
      }

    val (fst, snd) = xs splitAt n
    merge(mSort4(fst), mSort4(snd))
  }
}

mSort4(nums)
mSort4(fruits)