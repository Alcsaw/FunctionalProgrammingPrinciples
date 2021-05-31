# Functional Programming Principles in Scala

This repository contains the follow up code from the course Functional Programming Principles in Scala I'm attending through Coursera. The course is available at https://www.coursera.org/learn/progfun1

# About the course

Some of the topics I studied in this course are:

### Week 1:
- Programming Paradigms
- Reasons to use Functional Programming
- Evaluation methods in Scala
  - Substitution model
  - call-by-name
  - call-by-value
- Short-circuit evaluation
- Tail Recursion

### Week 2
- High-Order Functions
- Currying
- Infix Notation
  - Relaxed Identifiers
  - Precedence Rules

### Week 3
- Persistent Data Structures
- Class Hierarchies
- Dynamic Binding
  - Dynamic method dispatch
- How classes are organized
  - packages and imports
  - single inheritance
  - Exceptions
- Polymorphism
  - Cons-Lists
  - Type Parameters
  - Type erasure
  
### Week 4
- Object-Oriented Programming in Scala
  - Creating classes to substitute primitive types
  - Peano numbers
  - Functions as Objects (trait with apply method)
  - Expansion of Function Values
  - Expansion of Function Calls
  - ETA-Expansion
- Polymorphism
  - subtyping and generics
  - bounds and variance
  - Type Bounds
    - upper bound: `[S <: IntSet]`
    - lower bound: `[S >: NonEmpty]` (so S could be one of NonEmpty, IntSet, AnyRef or Any)
    - mixed bounds: `[S >: NonEmpty <: IntSet]`
    - Covariance and Array typing problem (ArrayStoreException in Java)
  - Liscov Substitution Principle -> in Scala, Arrays are not covariant
- Variance
- Decomposition
  - better avoid using type tests and type casts in Scala (low-level and potentially unsafe)
  - Object-Oriented Decomposition (instead, one could use ⮌)
    - Limitations of OO Decomposition
- Pattern Matching
  - Functional Decomposition with Pattern Matching
  - Case Classes
  - Forms of Patterns and how match expressions are evaluated
- Lists
  - Constructors of List: `Nil` and `::` (pronounced cons)
  - List operations and patterns
  
### Week 5
- More functions on Lists
  - last, init, concat, reverse, removeAt, flatten
- Pairs and Tuples
  - Sorting Lists faster with merge sort
  - the Tuple class
- Implicit Parameters
  - Parametrization with Ordering
- Higher-Order List Functions
- Reduction of Lists
Extra credit:
- Reasoning about `concat`
  - structural induction
  - referential transparency
- A Larger Equational Proof on Lists
  - generalize the equation
  - fold/unfold method
