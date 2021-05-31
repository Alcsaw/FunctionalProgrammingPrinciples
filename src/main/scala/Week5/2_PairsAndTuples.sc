/*
Sorting Lists faster with merge sort
If the list consists of zero or one element, it's already sorted.
Otherwise,
1. Separate the list into two sub-lists, each containing around half of the elements of the original list.
2. Sort the two sub-lists.
3. Merge the two sorted sub-lists into a single sorted list.
 */

def mergeSort(xs: List[Int]): List[Int] = {
  val n = xs.length/2

  if (n == 0) xs
  else {
    def merge(xs: List[Int], ys: List[Int]): List[Int] = xs match {
      case Nil => ys
      case x :: xs1 => ys match {
        case Nil => xs
        case y :: ys1 =>
          if (x < y) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    }

    val (fst, snd) = xs splitAt n
    merge(mergeSort(fst), mergeSort(snd))
  }
}

val list = List(2, 3, 1, 6, 4, 0, -2)
mergeSort(list)

/*
The merge function as given uses a nested
pattern match. This does not reflect the
inherent symmetry of the merge algorithm.
Rewrite merge using a pattern matching over pairs.
 */
def mSort(xs: List[Int]): List[Int] = {
  val n = xs.length/2

  if (n == 0) xs
  else {
    def merge(xs: List[Int], ys: List[Int]): List[Int] =
      (xs, ys) match {
        case (Nil, zs) => zs
        case (zs, Nil) => zs
        case (w::ws, z::zs) =>
          if (w < z) w :: merge(ws, ys)
          else z :: merge(zs, xs)
      }

    val (fst, snd) = xs splitAt n
    merge(mSort(fst), mSort(snd))
  }
}

mSort(list)
