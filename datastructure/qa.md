
# Q&A

- Describe `Activity`/`Fragment`/`Service` Lifecycle?
  - Lifecycle when screen rotated?

- Thread / Handler / Looper / AsyncTask

- Implict / Explict intent

- Animation kinds

- What's the difference between `Vector` / `ArrayList` in JVM ?
  - Vector is `synchronized`

- RecyclerView's benifits?
  - Use `Paging Library` to load data gradually and gracefully <https://developer.android.com/jetpack/androidx/releases/paging>

- Processes / Threads
  - https://developer.android.com/guide/components/processes-and-threads

- What's the difference between 

```kt
//A
people.map(Person::name).filter { it.startsWith("A") }
//B
people.asSequence()                 
    .map(Person::name)                   
    .filter { it.startsWith("A") }     
    .toList()
```
  - sequence checks through elements one by one, return the first match then end. Thus it's much more efficiency while `map.filter` calcs the whole collection.
