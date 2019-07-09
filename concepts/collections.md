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

## More Filter with Lambda

```kt
val averageMobileDuration = log
    .filter { it.os in setOf(OS.IOS, OS.ANDROID) }
    .map(SiteVisit::duration)
    .average()
```

____

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
