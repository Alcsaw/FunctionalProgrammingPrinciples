import scala.annotation.tailrec
1 + 2

def abs(x: Double) = if (x < 0) -x else x

def sqrt(x: Double) = {

  def isGoodEnough(guess: Double) = {
    abs(guess * guess - x) / x < 0.001
  }

  def improve(guess: Double) =
    (guess + x / guess) / 2

  @tailrec
  def sqrtIter(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))

  sqrtIter(1.0)
}

sqrt(2)
sqrt(4)

sqrt(0.001)
sqrt(0.1e-20)
sqrt(1.0e20)
sqrt(1.0e50)
