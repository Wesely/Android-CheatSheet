# ReactiveX

## Gradle

``` gradle
    implementation 'io.reactivex.rxjava2:rxjava:2.2.9'
    implementation 'io.reactivex.rxjava2:rxkotlin:2.3.0'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'
    implementation 'com.trello.rxlifecycle3:rxlifecycle:3.0.0'
```

## RxClass Example

- Subscribtion :
  - compositeDisposable.add
  - LifeCycle(observable.`.compose()`)

- PublishSubject Pattern :

    ```Kotlin
        val source = PublishSubject.create<Int>()
        val ob = source.subscribe(...)
        for(i in 1..20)
            source.onNext(i)
        source.onComplete()
    ```

- Observable Pattern :

    ``` gradle
    val ob = Observable.just(100,200,300)
        val disposable = ob.map { "I got $it" }
            .compose(bindToLifecycle()).subscribeOn(Schedulers.io())
            .doOnDispose { Log.d("rxTest", "doOnDispose") }
            .doOnComplete { Log.d("rxTest", "doOnComplete"); }
            .doOnEach { Log.d("rxTest", "doOnEach") }
            .subscribe(
                { Log.d("rxTest", "onNext : $it") },
                { Log.d("rxTest", "onError") }
            )
    ```
