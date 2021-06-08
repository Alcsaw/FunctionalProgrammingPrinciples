/* Handling Nested Sequences

We can extend the usage of higher order functions
on sequences to many calculations which are
usually expressed using nested loops.

Example: Given a positive integer n, find all
pairs of positive integers i an j,
with 1 <= j < i < n such that i + j is prime.
For example, if n = 7, the sought pairs are
    i   | 2 3 4 4 5 6 6
    j   | 1 2 1 3 2 1 5
  ______________________
  i + j | 3 5 5 7 7 7 11

A natural way to do this is to:
  1. Generate the sequence of all pairs of
  integers (i, j) ushc that 1 <= j < i < n.
  2. Filter the pairs for which i + j is prime.

One natural way to generate the sequence of pairs
is to:
  1. Generate all integers i between 1 and n.
  2. For each integer i, generate the list of
  pairs (i, 1), ..., (i, i-1).
This can be achieved by combining until and map:
 */
val n = 7

(1 until n) map (i =>
  (1 until i) map (j => (i, j)))

/*
Generate Pairs
The previous step gave a sequence of sequences,
let's call it xss.
We can combine all the sub-sequences using
foldRight with ++:
 */
val xss = (1 until n) map (i =>
  (1 until i) map (j => (i, j)))

(xss foldRight Seq[(Int, Int)]())( _ ++ _)
/*
Or, equivalently, we use the built-in method
flatten
 */
xss.flatten

/*
Here's a useful law:
  xs flatMap f = (xs map f).flatten
Hence, the above expression can be simplified to
 */
(1 until n) flatMap (i =>
  (1 until i) map (j => (i, j)))

/*
Now, to filter the condition we need:
 */
def isPrime(n: Int): Boolean =
  (2 until n) forall (d => n % d != 0)

(1 until n) flatMap (i =>
  (1 until i) map (j => (i, j))) filter (pair =>
  isPrime(pair._1 + pair._2))

/*
For-Expressions

Higher-order functions such as map, flatMap or
filter provide powerful constructs for
manipulating lists. But sometimes the level of
abstraction required by these functions make the
program difficult to understand. In this case,
Scala's for expression notation can help.

Let persons be a list of elements of class Person,
with fields name and age.
 */
case class Person(name: String, age: Int)

/*
To obtain the names of persons over 20 years old,
you can write:
 */
val persons = List(Person("Augusto", 25),
                   Person("Emanuel", 6))

for (p <- persons if p.age > 20) yield p.name

/*
Which is equivalent to:
 */
persons filter (p => p.age > 20) map (p => p.name)

/*
The for-expressions is similar to loops in
imperative languages, except that it builds a
list of the results of all iterations.

Use of For:
 */
for {
  i <- 1 until n
  j <- 1 until i
  if isPrime(i + j)
} yield (i, j)

/*
Exercise:

Write a version of scalarProduct (from last
session) that makes use of a `for`.
 */
val vector1 = Vector(1, 2, 3.0)
val vector2 = Vector(3.0, 2, 1)

def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double = {
  (for ( (x, y) <- xs zip ys ) yield x * y).sum
}

scalarProduct(vector1, vector2)
