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


val t1 = new NonEmpty(3, new Empty, new Empty)
val t2 = t1 incl(4)

val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
val text = "I have a Galaxy phone"
val res = google.exists(keyword => text.contains(keyword))

println(res)
