#Sorting with Kotlin

## QuickSort

- If the elemenets are not Int? no worries, override the `compareTo()` of your collection

```kt
  fun quickSort(array: List<Int>): List<Int> {
      if (array.count()<=1)
          return array
      val anchor = array[0]
      val smaller = array.filter { it < anchor }
      val same = array.filter { it == anchor }
      val larger = array.filter { it > anchor }
      return smaller + same + larger
  }
```
