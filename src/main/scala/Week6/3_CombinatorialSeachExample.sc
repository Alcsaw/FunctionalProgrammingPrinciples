/*
Sets

Sets are another basic abstraction in the Scala
collections. A set is written analogously to a
sequence:
 */
val fruits = Set("apple", "banana", "pear")
val s = (1 to 6).toSet

/*
Most operations on sequences are also available
on sets (see Iterables Scaladoc for a list of
all supported operations):
 */
s map (_ + 2)
fruits filter (_.startsWith("app"))
s.nonEmpty

/*
The principal differences between sets and
sequences are:
  1. Sets are unordered; the elements of a set
  do not have a predefined order in which they
  appear in the set;
  2. Sets do not have duplicate elements;
  3. The fundamental operation on sets is contains
  (instead of `head` and `tail` like in Lists or
  indexes in Vectors).
 */
s map (_ / 2)
s contains 5

/*
Example: N-Queens

The eight queens problem is to place eight queens
on a chessboard so that no queen is threatened
by another. In other words, there can't be two
queens in the same row, column, or diagonal.
We now develop a solution for a chessboard of any
size, not just 8. One way to solve the problem is
to place a queen on each row. Once we have placed
k - 1 queens, one must place the kth queen in a
column where it's not "in check" with any other
queen on the board.

Algorithm

We can solve this problem with a recursive
algorithm:
  1. Suppose that we have already generated all
  the solutions consisting of placing k-1 queens
  on a board of size n.
  2. Each solution is represented by a list (of
  length k-1) containing the numbers of columns
  (between 0 and n-1).
  3. The column number of the queen in the k-1th
  row comes first in the list, followed by the
  column number of the queen in row k-2, etc.
  4. The solution set is thus represented as a set
  of lists, with one element for each solution.
  5. Now, to place the kth queen, we generate all
  possible extensions of each solution preceded by
  a new queen:
 */
def queens(n: Int): Set[List[Int]] = {
  def isSafe(col: Int, queens: List[Int]): Boolean = ???

  def placeQueens(k: Int): Set[List[Int]] =
    if (k == 0) Set(List())
    else
      for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens

  placeQueens(n)
}

/*
Exercise

Write the function `isSafe` which testes if a
queen placed in an indicated column col is secure
amongst the other placed queens. It is assumed
that the new queen is placed in the next available
row after the other placed queens (in other words:
in row queens.length).
 */
def queens(n: Int): Set[List[Int]] = {
  def isSafe(col: Int, queens: List[Int]): Boolean = {
    val currentRow = queens.length
    val placedQueens = (currentRow - 1 to 0 by -1) zip queens

    placedQueens forall {
      case (row, column) => column != col &&
        math.abs(col - column) != currentRow - row
    }
  }

  def placeQueens(k: Int): Set[List[Int]] =
    if (k == 0) Set(List())
    else
      for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens

  placeQueens(n)
}

queens(4)

def show(queens: List[Int]) = {
  val lines =
    for (col <- queens.reverse)
      yield Vector.fill(queens.length)("* ")
        .updated(col, "X ").mkString
  "\n" + (lines mkString "\n")
}

(queens(4) map show) mkString "\n"
(queens(8) take 3 map show) mkString "\n"

