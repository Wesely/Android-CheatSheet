# Package

## Glide

``` gradle
    implementation 'com.github.bumptech.glide:glide:4.9.0'
    implementation 'com.github.bumptech.glide:compiler:4.9.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'
```

## ReactiveX

``` gradle
    implementation 'io.reactivex.rxjava2:rxjava:2.2.9'
    implementation 'io.reactivex.rxjava2:rxkotlin:2.3.0'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'
    implementation 'com.trello.rxlifecycle3:rxlifecycle:3.0.0'
```

## Retrofit 2 feat. Gson

``` gradle
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
    // Make it returns RxObservable
    implementation "com.squareup.retrofit2:adapter-rxjava2:2.4.0"
    implementation 'com.google.code.gson:gson:2.8.5'
```

- Plugin : `JSON To Kotlin Class`
- Consider : Moshi

## LiveData & ViewModel

``` gradle
    implementation "android.arch.lifecycle:extensions:1.1.1"
```

## Data binding

``` gradle
    kapt "com.android.databinding:compiler:3.1.3"
```

## Dagger 2

``` gradle
    implementation "com.google.dagger:dagger:2.16"
    kapt "com.google.dagger:dagger-compiler:2.16"
    compileOnly "org.glassfish:javax.annotation:3.1.1"
```

## ViewPager2

``` gradle
     // For the latest version number of ViewPager2, please refer to the official page.
     // Link: https://developer.android.com/jetpack/androidx/releases/viewpager2
     implementation 'androidx.viewpager2:viewpager2:1.0.0-alpha04'
```

## UI

### Fonts

- Light

    `android:fontFamily="sans-serif-light"`

    `android:fontFamily="sans-serif-medium"`

- Bold

    `android:textStyle="bold"`
- TypeFace

    `android:typeface="sans"`

### Animations

- Interpolator
  - AccelerateDecelerateInterpolator
  - AnticipateOvershootInterpolator
  - AccelerateInterpolator
  - DecelerateInterpolator

- FrameAnimation : `AnimationDrawable()`

    ``` Kotlin
    var pb = ivProgressBar.drawable as AnimationDrawable
    pb.isOneShot = false // loop
    pb.start()
    ```

### Layout Positioning

- Set DrawableLeft

    `textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon, 0, 0, 0);`

### Expressions

- Numbers Format

    `%.2f%%` Display 2 digit after dot.

    `%,.2f` Digits will seperated by comma like `12,345.12`

    Example : `String.format("%,.2f", value)`

### Controlling

- Scrolling of `ViewPager` not smooth

    ``` Kotlin
    //OnPageChangeListener
        override fun onPageScrolled(param:Params..) {
            // to make it scroll smoothly inside scrollview
            pager.parent.requestDisallowInterceptTouchEvent(true)
        }
    ```

- Run On UI Thread

    ``` Kotlin
    Handler(Looper.getMainLooper()).post {
        // codes here
    }
    ```

## Rx

RxClass Example

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


# Data Binding

- Basic in Activity

    ``` Kotlin
    val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

    binding.user = User("Test", "User")
    ```

- LayoutInflater

    ``` Kotlin
    val binding: ActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater())
    ```

- Inside a Fragment, ListView, or RecyclerView adapter,

    ``` Kotlin
    val listItemBinding = ListItemBinding.inflate(layoutInflater, viewGroup, false)
    // or
    val listItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, viewGroup, false)
    ```