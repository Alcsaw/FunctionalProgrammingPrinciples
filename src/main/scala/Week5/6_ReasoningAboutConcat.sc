/*
Laws of Concat
Recall the concatenation operation ++ on lists.

We would like to verify that concatenation is
associative, and that it admits the empty list
`Nil` as neutral element to the left and to the
right:
 */
val xs, ys, zs = List(1, 2)
(xs ++ ys) ++ zs == xs ++ (ys ++ zs)
xs ++ Nil == xs
Nil ++ xs == xs

/*
How can we prove properties like these?
  By structural induction on lists.

Reminder: Natural Induction
Recall the principle of proof by natural
induction:
To show a property P(n) for all the integers
n >= b,
  - show that we have P(b) (base case),
  - for all integers n >= b show the induction
  step:
    if one has P(n), then one also has P(n + 1).

Example:
Given
 */
def factorial(n: Int): Int =
  if (n == 0) 1           // 1st clause
  else n * factorial(n-1) // 2nd clause

/*
Show that, for all n >= 4
 factorial(n) >= power(2, n)

Base case: 4
This case is established by simple calculations:
  `factorial(4) = 24 >= 16 = power(2, 4)`

Induction setp: n+1
We have for n >= 4:
  `factorial(n + 1)`

`>= (n + 1) * factorial(n)`
(by 2nd clause in factorial)

`> 2 * factorial(n)`
(by calculating)

`>= 2 * power(2, n)`
(by induction hypothesis)

`= power(2, n + 1)`
(bt definition of power)
 */
/*
Note that a proof can freely apply reduction steps
as equalities to some part of a term.
That works because pure functional programs don't
have side effects; so that a term is equivalent to
the term to which ir reduces.
This principle is called referential transparency.

Structural Induction
The principle of structural induction is analogous
to natural induction:
To prove a property P(xs) for all lists xs,
  - show that P(Nil) holds (base case),
  - for a list xs and some element x, show the
  induction step:
    if P(xs) holds, then P(x :: xs) also holds.

Example:
Let's show that, for lists xs, ys, zs:
  `(xs ++ ys) ++ zs = xs ++ (ys ++ zs)`
To do this, use structural induction on xs.
From the previous implementation of concat,
*/
def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List() => ys
  case x :: xs1 => x :: concat(xs1, ys)
}
/*
distill two defining clauses of ++:

Nil ++ ys = ys                        // 1st clause
(x :: xs1) ++ ys = x :: (xs1 ++ ys)   // 2nd clause

base case: Nil
For the left-hand side we have:
  (Nil ++ ys) ++ zs
  = ys ++ zs          // by 1st clause of ++
This case is, therefore, established.

Induction Step: LHS
                x :: xs
For the left-hand side, we have:
  ((x :: xs) ++ ys) ++ zs
  = (x :: (xs ++ ys)) ++ zs   // by 2nd clause of ++
  = x :: ((xs ++ ys) ++ zs)   // by 2nd clause of ++
  = x :: (xs ++ (ys ++ zs))   // by induction hypothesis

Induction Step: RHS

For the right side we have:
  (x :: xs) ++ (ys ++ zs)
  = x :: (xs ++ (ys ++ zs))   // by 2nd clause of ++
So this case (and with it, the property) is established.

Exercise

Show by induction on xs that xs ++ Nil = xs.
How many equations do you need for the
inductive step?

Base case: xs = Nil
  Nil ++ Nil
  = Nil               // by 1st clause

Induction step: xs :: xs
  (x :: xs) ++ Nil = x :: xs
  = x :: (xs ++ Nil)    // by 2ns clause
  = x :: xs             // by induction hypothesis


Exercise:

Prove the following distribution law for
map over concatenation. For any lists xs,
ys, function f:
  (xs ++ ys) map f = (xs map f) ++ (ys map f)
You will need the clauses of ++ as well
as the following clauses for map:

// clauses of ++
  Nil ++ xs = xs
  (x :: xs) ++ ys = x :: (xs ++ ys)

// clauses of map
  Nil map f = Nil
  (x :: xs) map f = f(x) :: (xs map f)
________________________________________________
Base case: xs = Nil
  LHS:
  (xs ++ ys) map f
  = (Nil ++ ys) map f
  = ys map f

  RHS:
  (xs map f) ++ (ys map f)
  = (Nil map f) ++ (ys map f)
  = Nil ++ (ys map f)     //by 1st clause of map
  = ys map f

* So base case is established

Induction Step: x :: xs
  LHS:
  (xs ++ ys) map f
  = ((x :: xs1) ++ ys) map f
  = (x :: (xs1 ++ ys)) map f // by concat 2nd clause
  = f(x) :: (xs1 ++ ys) map f // by map 2nd clause
  = f(x) :: (xs1 map f) ++ (ys map f) // by induction hypothesis

  RHS:
  (xs map f) ++ (ys map f)
  = ((x :: xs1) map f) ++ (ys map f)
  = f(x) :: (xs1 map f) ++ (ys map f)

Then we have LHS = RHS and the induction
is established.
 */
