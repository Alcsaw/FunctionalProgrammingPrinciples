/*
Maps

Another fundamental collection type is the map.
A map of type Map[Key, Value] is a data structure
that associates keys with values of type Value.
Examples:
 */
val romanNumerals = Map("I" -> 1, "V" -> 5, "X" -> 10)
val capitalOfCountry = Map("US" -> "Washington",
  "Switzerland" -> "Bern")

/*
Maps are Iterables and Functions

Class Map[Key, Value] extends the collection type
Iterable[(Key, Value)] and also extends the function
type key => value, so maps can be used everywhere
functions can. In particular, maps can be applied to
key arguments:
 */
capitalOfCountry("US")

/*
Querying Map

Applying a map to a non-existing key gives an error:
 */
//capitalOfCountry("Andorra")
//java.util.NoSuchElementException: key not found: Andorra
/*
To query a map without knowing beforehand whether
it contains a given key, you can use the get
operation:
 */
capitalOfCountry get "US"
capitalOfCountry get "Andorra"
/*
The result of a get operation is an Option Value.

The Option type is defined as:
trait Option[+A]
case class Some[+A](value: A) extends Option[A]
object None extends Option[Nothing]

The expression map get key returns
  - None  if map does not contain the given key,
  - Some(x) if map associates the given key with
  the value x.

 Decomposing Option

 Since options are defined as case classes, they
 can be decomposed using pattern matching:
 */
def showCapital(country: String) =
  capitalOfCountry.get(country) match {
    case Some(capital) => capital
    case None => "missing data"
  }

showCapital("US")
showCapital("Andorra")
/*
Options also support quite a few operations of
the other collections.

Sorted and GroupBy

Two useful operation of SQL queries in
addition to for-expressions are groupBy and
orderBy. `orderBy` on a collection can be
expressed by sortWith and sorted.
 */
val fruit = List("apple", "pear", "orange",
  "pineapple")
fruit sortWith (_.length < _.length)
fruit.sorted
/*
groupBy is available on Scala collections. It
partitions a collection into a map of collections
according to a discriminator function f.

Example:
 */
fruit groupBy (_.head)

/*
Map Example

A polynomial can be seen as a map from exponents
to coefficients. For instance, x³ - 2x + 5 can
be represented with the map:
 */
Map(0 -> 5, 1 -> -2, 3 -> 1)
/*
Based on this observation, let's design a class
Polynom that represents polynomials as maps.
 */
//class Polynom(val terms: Map[Int, Double]) {
//  def + (other: Polynom) =
//    new Polynom(terms ++ (other.terms map adjust))
//
//  def adjust(term: (Int, Double)): (Int, Double) = {
//    val (exp, coeff) = term
//    terms get exp match {
//      case Some(coeff1) => exp -> (coeff + coeff1)
//      case None => exp -> coeff
//    }
//  }
//
//  override def toString: String =
//    (for ((exp, coeff) <- terms.toList.sorted.reverse) yield
//      coeff + "x^" + exp) mkString " + "
//}

//val p1 = new Polynom(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
//val p2 = new Polynom(Map(0 -> 3.0, 3 -> 7.0))
//p1 + p2

/*
Default Values

So far, maps were partial functions: Applying a
map to a key value in map(key) could lead to
an exception, if the key was not stored in the
map. There is an operation withDefaultValue that
turns a map into a total function:
 */
val cap1 = capitalOfCountry withDefaultValue "<unknown>"
cap1("Andorra")

//class Polynom(val terms0: Map[Int, Double]) {
//  val terms = terms0 withDefaultValue(0.0)
//
//  def + (other: Polynom) =
//    new Polynom(terms ++ (other.terms map adjust))
//
//  def adjust(term: (Int, Double)): (Int, Double) = {
//    val (exp, coeff) = term
//    exp -> (coeff + terms(exp))
//    }
//
//  override def toString: String =
//    (for ((exp, coeff) <- terms.toList.sorted.reverse) yield
//      coeff + "x^" + exp) mkString " + "
//}
//
//val p1 = new Polynom(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
//val p2 = new Polynom(Map(0 -> 3.0, 3 -> 7.0))
//p1 + p2
//p1.terms(7)

/*
More simplifying by removing the structure
notation from the Polynom constructor
(repeated parameters):
 */
//class Polynom(val terms0: Map[Int, Double]) {
//  def this(bindings: (Int, Double)*) = this(bindings.toMap)
//  val terms = terms0 withDefaultValue (0.0)
//
//  def +(other: Polynom) =
//    new Polynom(terms ++ (other.terms map adjust))
//
//  def adjust(term: (Int, Double)): (Int, Double) = {
//    val (exp, coeff) = term
//    exp -> (coeff + terms(exp))
//  }
//
//  override def toString: String =
//    (for ((exp, coeff) <- terms.toList.sorted.reverse) yield
//      coeff + "x^" + exp) mkString " + "
//}
//
//val p1 = new Polynom(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
//// But we can still use the Map structure
//val p2 = new Polynom(Map(0 -> 3.0, 3 -> 7.0))
//p1 + p2
//p1.terms(7)

/*
Exercise:

The + operation on Polynom used map concatenation
with ++. Design another version of + in terms of
foldLeft.

def + (other: Polynom) =
  new Polynom((other.terms foldLeft ???)(addTerm))

def addTerm(terms: Map[Int, Double], term: (Int, Double)) =
  ???
 */


class Polynom(val terms0: Map[Int, Double]) {
  def this(bindings: (Int, Double)*) = this(bindings.toMap)
  val terms = terms0 withDefaultValue (0.0)

  def + (other: Polynom) =
    new Polynom((other.terms foldLeft terms)(addTerm))

  def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
    val (exp, coeff) = term
    terms + (exp -> (coeff + terms(exp)))
  }

  override def toString: String =
    (for ((exp, coeff) <- terms.toList.sorted.reverse) yield
      coeff + "x^" + exp) mkString " + "
}

val p1 = new Polynom(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
val p2 = new Polynom(0 -> 3.0, 3 -> 7.0)
p1 + p2
p1.terms(7)
/*
The foldLeft version of `+` function should be
more efficient since it adds terms directly into
the resulting List instead of creating an
intermediate list and concatenating it afterwards.
 */
