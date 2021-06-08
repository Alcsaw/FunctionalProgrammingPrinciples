/*
Vector has more evenly balanced access patterns
than List, so it is a preferable way to use
sequences when many indexes access are needed.
Lists are preferable when the access pattern
`head` and `tail` are used.
 */
val nums = Vector(1, 2, 3, -88)
val people = Vector("Bob", "James", "Peter")

/*
Operations on Vectors

Vectors are created analogously to lists.
They support the same operations as lists, with
the exception of `::`. Instead of `x :: xs`,
there is
  `x +: xs`   Create a new vector with leading
  element x, followed by all elements of xs.
  `xs :+ x`   Create a new vector with trailing
  element x, preceded by all elements of xs.
Note that the `:` always points to the sequence.

Collection Hierarchy

       Iterable
      /    |    \
    Seq   Set   Map
  /  |  \
List | Vector
     |
   Range

Arrays and Strings

Arrays and Strings support the same operations
as Seq and can implicitly be converted to
sequences where needed. They cannot be subclasses
of Seq because they come from Java.
 */
val xs: Array[Int] = Array(1, 2, 3)
xs map (x => 2 * x)

val ys: String = "Hello, World!"
ys filter (_.isUpper)

/*
Ranges

Another simples kind of sequence is the `range`.
Ir represents a sequence of evenly spaced
integers. It has three operators:
  - `to` (inclusive);
  - `until` (exclusive);
  - `by` (to determine step value)
Ranges are represented as single objects with
three fields: lower bound, upper bound, step value.
 */
val r: Range = 1 until 5
r.foreach(println)
val s: Range = 1 to 5
r.foreach(println)
val t = 1 to 10 by 3
t.foreach(println)
val u = 6 to 1 by -2
u.foreach(println)

/*
Some more operations on Sequences
 */
ys exists (c => c.isUpper)
ys forall (c => c.isUpper)

val pairs = List(1, 2, 3) zip ys

pairs.unzip

ys flatMap (c => List('.', c))
(ys flatMap (c => List('.', c))).toString()

xs.sum
xs.max

/*
Example: Combinations

To list all combinations of numbers x and y
where x is drawn from 1..M and y is drawn from
1..N:
 */
val M = 5
val N = 6
(1 to M) flatMap (x => (1 to N) map (y => (x, y)))

/*
Example: Scalar Product

To compute the scalar product of two vectors:
 */
def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
  (xs zip ys).map(xy => xy._1 * xy._2).sum

val vector1 = Vector(1, 2, 3.0)
val vector2 = Vector(3.0, 2, 1)

scalarProduct(vector1, vector2)

/*
An alternative way to write this is with a
pattern matching function value:
 */
def scalarProduct2(xs: Vector[Double], ys: Vector[Double]): Double =
  (xs zip ys).map{ case (x, y) => x * y }.sum

scalarProduct2(vector1, vector2)

/*
Generally, the function value
  { case p1 => e1 ... case pn => en }
is equivalent to
  x => x match { case p1 => e1 ... case pn en }
 */
/*
Exercise:
A number n is prime if the only divisors of n
are 1 and n itself. What is a high-level way to
write a test for primality of numbers?
For once, value conciseness over efficiency.
 */
def isPrime(n: Int): Boolean =
  (2 until n) forall (d => n % d != 0)

isPrime(1)
isPrime(3)
isPrime(4)
isPrime(5)
isPrime(6)
isPrime(7)
isPrime(8)