# Collections

- MaxBy
  - `people.maxBy { it.age }`
  - `people.maxBy(Person::age)`

- Sort
- SortDesending
- joinToString()
  - `people.joinToString(" ") { p: Person -> p.name }`
- `forEach{}`

- groupBy: converting a list to a map of groups
  - `people.groupBy { it.age }`
- flatMap
- assequences

____

### With

```kt
fun alphabet() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
    toString()
}
```

### Apply

```kt
fun alphabet() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()
```

```kt
  TextView(context).apply {
      text = "Sample Text"
      textSize = 20.0
      setPadding(10, 0, 0, 0)
  }
```

## Applying a predicate to a collection

```kt
val canBeInClub27 = { p: Person -> p.age <= 27 }
```

- all()
  - `people.all(canBeInClub27)` : Dose all `people` returns true?
- any()
  - `people.any(canBeInClub27)` : Any `people` returns true?
- count()
  - `people.count(canBeInClub27)`
  - More efficient than `people.filter(canBeInClub27).size` because needs not to create a intermediate collection.
- find()

## Sequence : 

Sequences give you an alternative way to perform such computations that avoids the creation of intermediate temporary objects.

```kt
//A
people.map(Person::name).filter { it.startsWith("A") }
//B
people.asSequence()                 
    .map(Person::name)                   
    .filter { it.startsWith("A") }     
    .toList()
```
 B has no intermediate collections to store the elements are created, so performance for a large number of elements will be noticeably better.
 
### Generate and use Sequence
 
```kt
val naturalNumbers = generateSequence(0) { it + 1 }
val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
```
