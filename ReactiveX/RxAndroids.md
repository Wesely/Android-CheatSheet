# ReactiveX

## Gradle

``` gradle
    implementation 'io.reactivex.rxjava2:rxjava:2.2.9'
    implementation 'io.reactivex.rxjava2:rxkotlin:2.3.0'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'
    implementation 'com.trello.rxlifecycle3:rxlifecycle:3.0.0'
```

## Observable Types

1. `Observable<T>`

    - Emits **0 to n** datas with `onNext()`,
    - Must ends with `onError` or `onComplete`

2. `Flowable<T>`

    - Emits **0 to n** datas with `onNext()`,
    - Must ends with `onError` or `onComplete`
    - Only Flowable supports backpressure

3. `Single<T>`

    - Emit only once, can be `onSuccess(data)` or `onError`

4. `Completable`

    - Never emits data
    - Only emits `onComplete` and `onError`

5. `Maybe<T>`
    - Emit onlu once, like `Single`
    - Never emits data, like `Completable`
    - That means: Only emits `onComplete` and `onError`, and only one event can be emitted.

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

  - Create Directly

    ```kt
        Observable.create<Int> {emitter->
            emitter.onNext(50)
            emitter.setCancellable {  }
        }
    ```

  - Create using `just()`

    ```kt
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

## RxView

Supports RxView, can be used to deounce and many other UI events.

```gradle
    implementation 'com.jakewharton.rxbinding3:rxbinding:3.0.0-alpha2'
```

- `RxBlinding` already contains `RxJava`, needs not to include again.

### Usage

```kt
addDisposable(RxView.clicks(btnClick)
        .throttleFirst(1, TimeUnit.SECONDS)
        .subscribe(o -> {
            Log.e("rxView", "clicked within period");
        }));
```

also supports `RxView.drags()`, `RxView.longClicks()`
