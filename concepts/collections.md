# Collections

- MaxBy
  - `people.maxBy { it.age }`
  - `people.maxBy(Person::age)`

- Sort
- SortDesending
- joinToString()
  - `people.joinToString(" ") { p: Person -> p.name }`