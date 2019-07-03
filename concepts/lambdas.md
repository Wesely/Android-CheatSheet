- Lambdas allow you to pass chunks of code as arguments to functions.
- Kotlin lets you pass lambdas to functions outside of parentheses and refer to a single lambda parameter as it.
- Code in a lambda can access and modify variables in the function containing the call to the lambda.
- You can create references to methods, constructors, and properties by prefixing the name of the function with ::, and pass such references to functions instead of lambdas.
- Most common operations with collections can be performed without manually iterating over elements, using functions such as filter, map, all, any, and so on.
- Sequences allow you to combine multiple operations on a collection without creating collections to hold intermediate results.
- You can pass lambdas as arguments to methods that take a Java functional interface (an interface with a single abstract method, also known as a SAM interface) as a parameter.
- Lambdas with receivers are lambdas in which you can directly call methods on a special receiver object.
- The with standard library function allows you to call multiple methods on the same object without repeating the reference to the object. apply lets you construct and initialize any object using a builder-style API.


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

### Let

```kt
email?.let { email -> sendEmailTo(email) }
user?.let { sendEmailTo(it.email) }
```

The let function will be called only if the email value is non-null, so you use the email as a non-null argument of the lambda

## Sequence

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
