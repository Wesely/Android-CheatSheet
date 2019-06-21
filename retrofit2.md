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
// testing
    testImplementation 'com.squareup.okhttp3:mockwebserver:3.8.1'
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
        fun getWeather(@Query("q") q: String): Observable<Weather> 
        // Query annotation needs not ?name=query format
    }
}
```

### Raw Text Body

```kt
    @POST("style-me/key-converts/encode")
    @Headers("content-type: application/x-www-form-urlencoded")
    fun encode(
        @Body keyInfos: RequestBody
    ):Call<ResponseBody>
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

### Use Sync Execution for testing

```kt
class ConnectionTest {
    @Test
    fun checkProductApi() {
        val bodyResponse = RetroApi.styleMeService.getProduct().execute()
        val body = bodyResponse.body()!!.string()
        val products = Gson().fromJson<Products>(body, Products::class.java)
        assert(!products.empty)
        assert(products.pageItems.isNotEmpty())
    }
}
```

#### Test Raw Text Body

```kt
    @Test
    fun checkDecodeApi() {
        val bodyResponse = RetroApi.styleMeService.encode(
            RequestBody.create(
                MediaType.parse("application/x-www-form-urlencoded"),
                "keyInfos=[{\"ID\":1001285,\"ASSET_TYPE\":2,\"ASSET_SUBTYPE\":4,\"PRD_ATTR\":1,\"GENDER\":1,\"AGE\":1,\"DATA_ID\":99975,\"VERSION\":2},{\"ID\":1003153,\"ASSET_TYPE\":2,\"ASSET_SUBTYPE\":4,\"PRD_ATTR\":2,\"GENDER\":1,\"AGE\":1,\"DATA_ID\":99974,\"VERSION\":2}]"
            )
        ).execute()
        val body = bodyResponse.body()!!.string()
        assert(body.isNotEmpty())
        assert(body.contains("0"))
    }
```

## Combine with OkHttp

OkHttp package are included in Retrofit2, does not need to add dependency.

```kt
    companion object {

        fun createCorService(): RestApiService {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://androidwave.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(RestApiService::class.java)
        }
    }
```
