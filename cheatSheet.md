# Package

## At the Top of Gradle

``` gradle
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
apply plugin: 'kotlin-kapt' // for annotation
```

## ANKO

- `asReference()`, 
- https://github.com/kotlin/anko

``` gradle
    implementation "org.jetbrains.anko:anko:0.10.8"
```

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

## Room

1. Put `apply plugin: 'kotlin-kapt'` at the top of `app.gradle`

2. Add dependencies:

    ``` gradle
        def room_version = "2.1.0-rc01"

        implementation "androidx.room:room-runtime:$room_version"
        annotationProcessor "androidx.room:room-compiler:$room_version" // For Kotlin use kapt instead of annotationProcessor

        // optional - Kotlin Extensions and Coroutines support for Room
        implementation "androidx.room:room-ktx:$room_version"

        // optional - RxJava support for Room
        implementation "androidx.room:room-rxjava2:$room_version"

        // optional - Guava support for Room, including Optional and ListenableFuture
        implementation "androidx.room:room-guava:$room_version"

        // Test helpers
        testImplementation "androidx.room:room-testing:$room_version"
    ```

# Coroutine with Kotlin

```kotlin
dependencies {
    ...
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1"
}
```

## UI

### Fonts

- AutoSize

    `android:autoSizeTextType="uniform"`

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

- Scrolling conflict

  - Override `dispatchTouchEvent()`
  - override `onInterceptTouchEvent()`

- Run On UI Thread

    ``` Kotlin
    Handler(Looper.getMainLooper()).post {
        // codes here
    }
    ```

## Data Binding

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

## Kotlin Things

### Class delegation: using the `by` keyword

``` Kotlin
class DelegatingCollection<T>(
        innerList: Collection<T> = ArrayList<T>()
) : Collection<T> by innerList {}
```

### coerceIn()

```kt
fun showProgress(progress: Int) {
    val percent = progress.coerceIn(0, 100)
    println("We're ${percent}% done!")
}
// result : >>> showProgress(146)
// We're 100% done!
```
