/*
Task

Phone keys have mnemonics assigned to them.
Assume you are given a dictionary `words` as a list of words.
Design a method `translate` such that `translate(phoneNumber)`
produces all phrases of words that can serve as mnemonics for
the phone number. For example, the phone number "7225247386",
should have the mnemonic "Scala is fun" as one element of the
set of solution phrases.
(This example was taken from: Lutz Prechelt: An Empirical
Comparison of Seven Programming Languages. IEEE Computer
33(10): 23-29 (2000)
 */

import scala.io.Source

val url = "https://www.epfl.ch/labs/lamp/wp-content/uploads/2019/01/linuxwords.txt"
val in = Source.fromURL(url)
val words = in.getLines.toList filter (word => word forall (chr => chr.isLetter))
val mnemonics = Map(
  '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
  '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")

/*
Invert the mnemonic map to give a map from chars 'A' ... 'Z' to '2' ... '9'
 */
val charCode: Map[Char, Char] =
  for ((digit, str) <- mnemonics; ltr <- str) yield (ltr -> digit)

/*
Maps a word to the digit string it can represent, e.g. "Java" -> "5282"
 */
def wordCode(word: String): String =
  //word.toUpperCase.map(charCode)  // Remember Maps are functions!
  //and the above can also be written like
  word.toUpperCase map charCode

wordCode("JAVA")
wordCode("java")

/*
A map from digit strings to the words that represent them,
e.g. "5282" -> List("Java", "Kata", "Lava", ...)
Note: A missing number should map to the empty set. e.g.
"1111" -> List()
 */
val wordsForNum: Map[String, Seq[String]] =
  words groupBy wordCode withDefaultValue Seq()

/*
Return all ways to encode a number as a list of words
 */
def encode(number: String): Set[List[String]] =
  if (number.isEmpty) Set(List())
  else {
    for {
      split <- 1 to number.length
      word <- wordsForNum(number take split)
      rest <- encode(number drop split)
    } yield word :: rest
  }.toSet

encode("7225247386")

def translate(number: String): Set[String] =
  encode(number) map (_.mkString(" "))

translate("7225247386")
