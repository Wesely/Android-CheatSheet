# High-End / Higher-Order Functions

## WithLock

This is an `extension` function of `Lock`

```kt
val l: Lock = ...
l.withLock {
    // access the resource protected by this lock
}
```

## The `use()` funciton

`use()` function is an extension function called on a **closable** resource.

The function calls the lambda and **ensures that the resource is closed**, regardless of whether the lambda completes normally or throws an exception.

For Example,

```java
/* Java */
static String readFirstLineFromFile(String path) throws IOException {
    try (BufferedReader br =
                   new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
}
```

Can be replaced with

```kt
fun readFirstLineFromFile(path: String): String {
    BufferedReader(FileReader(path)).use { br ->
        return br.readLine()
    }
}
```

____

## `return` with label

```kt
    // they both work
    people.forEach label@{
        if (it.name == "Alice") return@label
    }

    people.forEach {
        if (it.name == "Alice") return@forEach
    }
```

## `this` with label

```kt
println(StringBuilder().apply sb@{
   listOf(1, 2, 3).apply {
       this@sb.append(this.toString())
   }
})
```

____

## Anonymous functions

```kt
people.filter(fun (person): Boolean {
    return person.age < 30
})
```

or with expression body

```kt
people.filter(fun (person) = person.age < 30)
```

- If there is a `return` inside the anonymous function, that will return to the anonymous function.

```kt

fun lookForAlice(people: List<Person>) {
    people.forEach(fun (person) {
        if (person.name == "Alice") return // will return the fun(person)
        println("${person.name} is not Alice")
    })
}
```
