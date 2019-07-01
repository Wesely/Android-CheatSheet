# Operator Overloading

```kt
operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}
```

This makes you can use `pointA + pointB`

- Between different types

```kt
operator fun Point.times(scale: Double): Point {
    return Point((x * scale).toInt(), (y * scale).toInt())
}
```

Thus you can use `p * 1.5`

## Equality (Comparator)

- `a == b` will call `a.equals(b)`
- `a >= b` will call `a.compareTo(b)`
- `a in b` -> `b.contains(a)`

## Range / RangeTo

- `start..end` -> `start.rangeTo(end)`



## Expression to Function Name

- `a * b` → `times`
- `a / b` → `div`
- `a % b` → `mod`
- `a + b` → `plus`
- `a - b` → `minus`
- `+a`  → `unaryPlus`
- `-a`  → `unaryMinus`
- `!a` → `not`
- `++a`, `a++`  → `inc`
- `--a`, `a--`  → `dec`

## NO SPECIAL OPERATORS FOR BITWISE OPERATIONS

- `shl` —Signed shift left
- `shr` —Signed shift right
- `ushr` —Unsigned shift right
- `and` —Bitwise and
- `or` —Bitwise or
- `xor` —Bitwise xor
- `inv` —Bitwise inversion

There are only functions, example : `(0x0F and 0xF0)`

## Unary operators

- `+a`	unaryPlus
- `-a`	unaryMinus
- `!a`	not
- `++a`, `a++`	inc
- `--a`, `a--`	dec

## Overloading compound assignment operators

- A ` a += n` will call both `a = a.plus(n)` and `a = plusAssign(n)`, so do not implement both of them.
  - If class is immutable, use `plus`
  - If class is mutable, like a builder, can use `plusAssign`

## Get/Set

```kt 
operator fun Point.get(index: Int): Int {...}
```

Override `get`/`set` to use `val value = map[key]`

## Destructuring declarations and component functions

```console
>>> val p = Point(10, 20)
>>> val (x, y) = p             
>>> println(x)
10
>>> println(y)
20
```

These functions are auto generated for `data class`

```kt
class Point(val x: Int, val y: Int) {
    operator fun component1() = x
    operator fun component2() = y
}
```

## By Delegate (or any other class)

```kt
// define a class
class Delegate {
    // provide get and set
    operator fun getValue(...) { ... }          
    operator fun setValue(..., value: Type) { ... }  
}

class Foo {
    // and we can use this notation
    var p: Type by Delegate() 
}
```
