# Generics

Basic of Generics are skipped.

## Reified Type

`reified` type parameters can only be used in `inline` functions

Types info are wiped out in runtime for less memory use. If you want to keep it, you need `reified` (and `inline`)
But they can't be called from Java code.

-  Without Reified 

```kt
fun <T> checkType(value: Any) = value is T
//  Error: Cannot check for instance of erased type: T
```

- With Reified

```kt
inline fun <reified T> checkType(value: Any) = value is T  
//  >>> println(isA<String>("abc"))
//  true
```

