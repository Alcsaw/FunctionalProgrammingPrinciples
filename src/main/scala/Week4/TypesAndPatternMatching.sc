abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

class NonEmpty(elem: Int, left: IntSet, rigth: IntSet) extends IntSet {
  override def contains(x: Int): Boolean = {
    if (x < elem) left contains(x)
    else if (x > elem) rigth contains(x)
    else true
  }

  override def incl(x: Int): IntSet = {
    if (x < elem)new NonEmpty(elem, left incl(x), rigth)
    else if (x > elem) new NonEmpty(elem, left, rigth incl(x))
    else this
  }

  override def union(other: IntSet): IntSet =
    ((left union rigth) union other) incl elem

  override def toString: String = "{" + left + elem + rigth + "}"
}

class Empty extends IntSet {
  override def contains(x: Int): Boolean = false

  override def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)

  override def union(other: IntSet): IntSet = other

  override def toString: String = "."
}

val a: Array[NonEmpty] = Array(new NonEmpty(1, new Empty, new Empty))
//val b: Array[IntSet] = a  // in Scala, Arrays are not covariant
//b(0) = new Empty
val s: NonEmpty = a(0)

/*
Some types should be covariant whereas others should not.
Roughly speaking, a type that accepts mutations of its elements should not be covariant.
But immutable types can be covariant, if some conditions on methods are met.

Definition of Variance
Say C[T] is a parameterized type and A, B are types such that A <: B.
In general, there are three possible relationships between C[A] and C[B]:
- C[A] <: C[B]                                      C is covariant
- C[A] >: C[B]                                      C is contravariant
- neither C[A] nor C[B] is a subtype of the other   C is nonvariant

Scala lets you declare the variance of a type by annotating the type parameter:
- class C[+A] { ... }                               C is covariant
- class C[-A] { ... }                               C is contravariant
- class C[A] { ... }                                C is nonvariant
 */