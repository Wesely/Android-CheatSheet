# Class, Object and Interface

## Property in Interface

``` Kotlin
interface User {
    val email: String
    val nickname: String
        get() = email.substringBefore('@')
}
```

- A backing field would require storing state in an interface, which isn’t allowed.

    Therefore, email must be overridden in subclasses.

- But interface can contain properties with getters and setters, as long as they don’t reference a backing field.

    So nickname can be inherited.

## Accessing the backing field in a setter

``` Kotlin
class User(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            field = value
        }
}
```

### Can set the visibility of `get` or `set`

```Kotlin
class LengthCounter {
    var counter: Int = 0
        private set
    fun addWord(word: String) {
        counter += word.length
    }
}
```

This makes the `counter` can't be assigned directly.

## Singleton

``` Kotlin
object MySingleton{
    @Synchronized
    get()=...
    @Synchronized
    set()=...
}

## Difference between `Interface` and `abstract class`

``` Kotlin
//Gist : https://gist.github.com/Wesely/ef2ca681a7795802edefdd138db38c3c

abstract class ExampleAbstract(val zero: Int =0) {
    companion object {
        const val one = 1
        var two = 2
    }
    abstract var num :Int?
    val three = 3
    var four = 4
    fun getTheOne() = one
    open fun getTheTwo(): Int { return two }
    final fun getTheThree() = 3 // default final, redundant
    abstract fun getTheFour(): Int
}

class TryAbstract:ExampleAbstract(/*zero*/){
    override var num: Int?
        get() = 0
        set(value) {}

    // Can override or not
    override fun getTheTwo(): Int {
        return super.getTheTwo()
    }

    override fun getTheFour() = 4

}

interface ExampleInterface { // interface may not have a constructor
    companion object {
        const val one = 1
        var two = 2
    }
    abstract var num :Int?
    val three = 3 // Error, property initializers are not allowed
    var four = 4  // Error, property initializers are not allowed
    fun getTheOne() = one
    open fun getTheTwo(): Int { return two }  // default open, open is redundant
    final fun getTheThree() = 3             // Error, final is not allowed in interface
    abstract fun getTheFour(): Int          // default open, abstract is redundant
}

class TryInterface(override var num: Int?) :ExampleInterface{
    // Can override, or not
    override fun getTheOne(): Int { //
        return super.getTheOne()
    }

    // MUST override
    override fun getTheFour(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
```

- `Interfaces` in Kotlin are similar to Java’s but **can** contain default implementations (which Java supports only since version 8) and properties.
- All declarations are **final** and **public** by default.
- To make a declaration non-final, mark it as `open`.
- `internal` declarations are visible in the same module.
- Nested classes aren’t `inner` by default. Use the keyword `inner` to store a reference to the outer class.
- A `sealed` class can only have subclasses nested in its declaration (Kotlin 1.1 will allow placing them anywhere in the same file).
- Initializer blocks and secondary constructors provide flexibility for initializing class instances.
- You use the `field` identifier to reference a property backing field from the accessor body.
- `data` classes provide compiler-generated `equals`, `hashCode`, `toString`, `copy`, and other methods.
- Class delegation helps to avoid many similar delegating methods in your code.
- `object` declaration is Kotlin’s way to define a singleton class.
- `companion object` (along with package-level functions and properties) replace Java’s static method and field definitions.
- `companion object`, like other objects, can implement interfaces, as well as have extension functions and properties.
- **Object expression**s are Kotlin’s replacement for Java’s anonymous inner classes, with added power such as the ability to implement multiple interfaces and to modify the variables defined in the scope where the object is created.
