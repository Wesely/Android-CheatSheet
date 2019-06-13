# Class, Object, Abstract, Interface, with Getters and Setters

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

## Singleton

``` Kotlin
object MySingleton{
    @Synchronized
    get()=...
    @Synchronized
    set()=...
}
