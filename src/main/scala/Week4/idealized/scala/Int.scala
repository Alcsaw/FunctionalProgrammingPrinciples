package Week4.idealized.scala

abstract class Int {
  def + (that: Double): Double
  def + (that: Float): Float
  def + (that: Long): Long
  def + (that: Int): Int

  def << (cnt: Int): Int

  def & (that: Long): Long
  def & (that: Int): Int

  def == (that: Double): Boolean
  def == (that: Float): Boolean
  def == (that: Long): Boolean
}
