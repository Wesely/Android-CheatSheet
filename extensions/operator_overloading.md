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

## Overloading compound assignment operators

```kt
operator fun <T> MutableCollection<T>.plusAssign(element: T) {
    this.add(element)
}
```


