class Rational(x: Int, y: Int) {
  require(y != 0, "Denominator must be nonzero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int =
  /*
  * Calculates the great common divisor
  * between a and b
  */
    if (b == 0) a else gcd(b, a % b)

  private val g = gcd(x, y)

  def numerator = x / g
  def denominator = y / g

  //def add(that: Rational) =
  def + (that: Rational) =
    new Rational(
      numerator * that.denominator +
        that.numerator * denominator,
      denominator * that.denominator
    )

  override def toString: String =
    numerator + "/" + denominator

  //def neg: Rational =
  def unary_- : Rational =
    new Rational(
      - numerator,
      denominator
    )

  //def sub(that: Rational) = add(that.neg)
  //def - (that: Rational) = this + that.neg
  def - (that: Rational) = this + -that

  def < (that: Rational) = {
  // def less (that: Rational) = {
    // Verifies if the Rational is less then another
    numerator * that.denominator < that.numerator * denominator
  }

  def max(that: Rational) =
    //if (this.less(that)) that else this
    if (this < that) that else this
}

val x = new Rational(1, 2)

x.numerator
x.denominator

def addRational(r: Rational, s: Rational): Rational =
  new Rational(
    r.numerator * s.denominator + s.numerator * r.denominator,
    r.denominator * s.denominator
  )

def makeString(r: Rational) =
  r.numerator + "/" + r.denominator

makeString(addRational(new Rational(1, 2), new Rational(2, 3)))

// now using the new methods
// (functions inside the class definition)
val y = new Rational(2, 3)
//x.add(y)
x + y

val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)
//x.sub(y).sub(z)
x - y - z
//y.add(y)
y - y

//x.less(y)
x < y
x.max(y)