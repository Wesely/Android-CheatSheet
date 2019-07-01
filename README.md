# Android-CheatSheet, Kotlin things

## Parcelable

### NO MORE IMPLEMENTATION!

```kt
@Parcelize
data class Category(
    @SerializedName("fldMainId")
    val fldMainId: Int,
    @SerializedName("fldMainName")
    val fldMainName: String,
    @SerializedName("fldUpdateTime")
    val fldUpdateTime: Int
):Parcelable
```

- There will be an known IDE error that tells you `Class X is not abstract and does not implement fun writeToParcel() defined in android.os.Parcelable`, just ignore it (July, 2019)