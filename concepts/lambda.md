# Lambda Expression

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