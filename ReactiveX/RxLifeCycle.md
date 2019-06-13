# RxLifeCycle

> https://github.com/trello/RxLifecycle

## Installation

``` gradle
implementation 'com.trello.rxlifecycle3:rxlifecycle:3.0.0'

// If you want to bind to Android-specific lifecycles
implementation 'com.trello.rxlifecycle3:rxlifecycle-android:3.0.0'

// If you want pre-written Activities and Fragments you can subclass as providers
implementation 'com.trello.rxlifecycle3:rxlifecycle-components:3.0.0'

// If you want pre-written support preference Fragments you can subclass as providers
implementation 'com.trello.rxlifecycle3:rxlifecycle-components-preference:3.0.0'

// If you want to use Android Lifecycle for providers
implementation 'com.trello.rxlifecycle3:rxlifecycle-android-lifecycle:3.0.0'

// If you want to use Kotlin syntax
implementation 'com.trello.rxlifecycle3:rxlifecycle-kotlin:3.0.0'

// If you want to use Kotlin syntax with Android Lifecycle
implementation 'com.trello.rxlifecycle3:rxlifecycle-android-lifecycle-kotlin:3.0.0'

// If you want to use Navi for providers
// DEPRECATED: Use rxlifecycle-android-lifecycle instead. This will be removed in a future release.
implementation 'com.trello.rxlifecycle3:rxlifecycle-navi:3.0.0'
```

## RxActivities

### Components

``` Kotlin
class MyActivity2 : RxAppCompatActivity()
class MyActivity2 : RxFragmentActivity()
class MyActivity2 : RxFragment()
// ...etc
```

### Usage

``` Kotlin
RxCart.getCart().observeOn(AndroidSchedulers.mainThread())
    .compose(bindToLifecycle())
    .subscribe(
        { // TODO },
        { throw Exception("RxCart Error") }
    )
```
