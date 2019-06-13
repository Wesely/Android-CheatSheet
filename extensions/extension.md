# Extension for Kotlin

## Extend a property

- A `val` property must have a getter

``` Kotlin
val MyView.extStr: String
    get() = "String"
```

- A `var` property must have a setter (and getter)

``` Kotlin
var MyView.ext: Int
    get() = 0
    set(value:Int) {ext = value}
```
