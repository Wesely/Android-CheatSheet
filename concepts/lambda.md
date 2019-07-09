# Lambda Expression

Lambdas are normally compiled to anonymous classes. But that means every time you use a lambda expression, an extra class is created; and if the lambda captures some variables, then a new object is created on every invocation.

If you mark a function with the `inline` modifier, the compiler won’t generate a function call when this function is used and instead will replace every call to the function with the actual code implementing the function.

- Lambdas allow you to pass chunks of code as arguments to functions.
- Kotlin lets you pass lambdas to functions outside of parentheses and refer to a single lambda parameter as it.
- Code in a lambda can access and modify variables in the function containing the call to the lambda.
- You can create references to methods, constructors, and properties by prefixing the name of the function with `::`, and pass such references to functions instead of lambdas.
- Most common operations with collections can be performed without manually iterating over elements, using functions such as `filter`, `map`, `all`, `any`, and so on.
- **Sequences** allow you to combine multiple operations on a collection without creating collections to hold intermediate results.
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

## Declare

```kt
fun twoAndThree(operation: (Int, Int) -> Int) { 
    val result = operation(2, 3)  
    println("The result is $result")
}
>>> twoAndThree { a, b -> a + b }
The result is 5
>>> twoAndThree { a, b -> a * b }
The result is 6
```

## Passing callback

```kt
fun performRequest(
       url: String,
       callback: (code: Int, content: String) -> Unit  
) {
    /*...*/
}
>>> val url = "http://kotl.in"
>>> performRequest(url) { code, content -> /*...*/ }   
>>> performRequest(url) { code, page -> /*...*/ }
copy
```

## Implement

```kt
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) sb.append(element)   
    }
    return sb.toString()
}

// Usage:
println("ab1c".filter { it in 'a'..'z' })  
// result: abc
```

- first `String` is receiver type

## Access from Java

```kt
/* Kotlin declaration */
fun processTheAnswer(f: (Int) -> Int) {
    println(f(42))
}

/* Java 8 */
processTheAnswer(number -> number + 1);
/// result >> 43

/* Older Java */
processTheAnswer(
    new Function1<Integer, Integer>() {       
        @Override
        public Integer invoke(Integer number) {
            System.out.println(number);
            return number + 1;
        }
    });
```

## As Parameter

```kt
fun <T> Collection<T>.joinToString(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = "",
        transform: (T) -> String = { it.toString() } 
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(transform(element))      
    }
    result.append(postfix)
    return result.toString()
}
```

`transform: (T) -> String = { it.toString() }`

- `transform` : parameter name
- `(T) -> String` : Any type, will return String
- ` = { it.toString() }` : Default implementation

______

## Returning functions from functions

```kt
enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)

fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double {    
    if (delivery == Delivery.EXPEDITED) {
        return { order -> 6 + 2.1 * order.itemCount }      
    }
    return { order -> 1.2 * order.itemCount }              
}
```

- input  : `(delivery: Delivery)`
- output : `(Order) -> Double`

Usage:

```powershell
>>> val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
>>> println("Shipping costs ${calculator(Order(3))}")    
Shipping costs 12.3
```