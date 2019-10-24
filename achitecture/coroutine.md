# `Coroutine` with Kotlin

- <https://kotlinlang.org/docs/tutorials/coroutines/coroutines-basic-jvm.html>

By default, coroutines are run on a shared pool of threads. Threads still exist in a program based on coroutines, but one thread can run many coroutines, so there's no need for too many threads.

And yes, coroutines do run in parallel.

```kotlin
dependencies {
    ...
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1"
}
```

## Usage

``` kotlin
launch {
    ...
}

// non-blocking
// it's parent might return(end) while non-blocking is still in process
GlobalScope.launch {
    delay(1000)
    println("Hello")
}

// blocking, this will make his parent function wait for it's result
runBlocking { 
    delay(2000)
}
```

### Async : returning value from `coroutine`

```kt
val c = AtomicLong()

for (i in 1..1_000_000L)
    GlobalScope.launch {
        c.addAndGet(i)
    }

println(c.get())
```

This is better and faster than creating 1,000,000 threads.

But needs to tell mainThread to wait for `coroutine` to complete, so we need `await()`

## `await()` function

```kt
// This function returns many `n`
val deferred = (1..1_000_000).map { n ->
    GlobalScope.async {
        n
    }
}
```

And add all of `n` by

```kt
runBlocking {
    val sum = deferred.sumBy { it.await() }
    println("Sum: $sum")
}
```

If use non-blocking method, compiler will prompt `Suspend functions are only allowed to be called from a coroutine or another suspend function`

_____

## Suspending functions

Now, let's say we want to extract our workload (which is "wait 1 second and return a number") into a separate function:

```kt
fun workload(n: Int): Int {
    delay(1000)
    return n
}
```

> ERROR:Suspend functions are only allowed to be called from a coroutine or another suspend function

Correct way:

```kt
suspend fun workload(n: Int): Int {
    delay(1000)
    return n
}
```

## Back to MainThread

> `java.lang.IllegalArgumentException: You must call this method on the main thread`

```kt
GlobalScope.launch {
    withContext(Dispatchers.Main) {
        Log.d("coroutineScope", "#runs on ${Thread.currentThread().name}")
    }
}
```

```kt
GlobalScope.launch {
    suspend {
        // This runs on another thread
        delay(10000)
        withContext(Dispatchers.Main){
            // This runs on Main Thread
        }
        withContext(Handler(Looper.getMainLooper()).asCoroutineDispatcher()) {
            // This ALSO runs on Main Thread
            // If Dispatchers.Main makes a bug, use this insdead
        }
    }.invoke()
}
```
## Example

- Assign the proper context for suspend functions

```kt
suspend fun runningIoTask(){
    runBlocking(Dispatcher.IO){
        // run some diskIO
    }
}
suspend fun runningStrangeTask(){
    runBlocking(Dispatcher.Unconfined){
        // run some Strange Task
    }
}
suspend fun runningUITask(){
    runBlocking(Dispatcher.Main){
        // run some UI change
    }
}
```
- Call them in a proper Scope

```kt
fun runAllTaskTogether(){
    CoroutineScope(/**(optional)**/ Dispatcher.Any).launch{
        runningIoTask()
        runningStrangeTask()
    }.invokeOnCompletion { throw -> /
        runningUITask() 
    }

    //or 
    viewModelScope.launch{ }.invokeOnCompletion { throw -> }

    //or 
    GlobalScope.launch {  }.invokeOnCompletion { throw -> }
}
```
