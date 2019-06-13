# Retrofit2 with RxJava2

``` gradle
// Retrofit itself
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
// with RxJava
    implementation "com.squareup.retrofit2:adapter-rxjava2:2.4.0"
// RxJava
    implementation 'io.reactivex.rxjava2:rxjava:2.2.9'
    implementation 'io.reactivex.rxjava2:rxkotlin:2.3.0'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'
```

## 1. Define Interface

``` Kotlin
class RetrofitHelper {
    companion object {
        // Callback Version
        var retrofit = lazy{
            Retrofit.Builder()
            .baseUrl("https://api.apixu.com/v1/")
            .build()
        }

        // RxObservser Version
        var rxRetrofit = lazy{
            Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create()) // use Gson
            .baseUrl("https://api.apixu.com/v1/")
            .build()
        }

    }

    interface WeatherService {
        @Headers(
            "X-RapidAPI-Host: community-open-weather-map.p.rapidapi.com",
            "X-RapidAPI-Key: d6255f91003e1c00280d"
        )
        @GET("current.json?key=7d32e2205&q=Linkou,tw&lang=zh_tw")
        fun getWeather(): Call<ResponseBody>
        @GET("current.json?key=7d32e22405&lang=zh_tw")
        fun getWeather(@Query("q") q: String): Observable<Weather> // Query annotation needs not ?name=query format
    }
}
```

## 2. Usage

### Callback form

``` Kotlin
val webService = RetrofitHelper.retrofit.create(RetrofitHelper.WeatherService::class.java)
val call = webService.getWeather()
call.enqueue(object : Callback<ResponseBody> {
    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        // TODO!!
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        Log.d("MyWidget", "callWeatherRequest got onFailure()")
        t.printStackTrace()
    }
})
```

### Observer form

``` Kotlin
val d = RetrofitHelper.rxRetrofit.create(RetrofitHelper.WeatherService::class.java)
        val dis = d.getWeather("taipei,tw")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("rxWeather", "success${it.location}");textView2.text = it.location.region },
                { Log.d("rxWeather", "err");it.printStackTrace() }
            )
```
