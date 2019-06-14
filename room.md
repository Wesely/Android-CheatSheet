# Room : Android Database

## Reference

- Official  Guide <https://developer.android.com/training/data-storage/room>
- 7 Pro-tips for Room <https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1>

## Dependencies

> Releas notes : <https://developer.android.com/jetpack/androidx/releases/room>

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

## Implementation

`User.kt`

``` kotlin

@Fts4 // Support full-text search
@Entity
// or If the entity has a composite primary key:
@Entity(primaryKeys = arrayOf("firstName", "lastName"))
// If wannna set the table name
@Entity(tableName = "users")
// using ignoredColumns to those you don't want to persist
@Entity(ignoredColumns = arrayOf("picture"))
data class User(
    @PrimaryKey val uid: Int,
    // use @ColumnInfo to set column names, or not use to use default
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
    // using @Ignore to those you don't want to persist
    @Ignore val picture: Bitmap?
)
```

- for more about FTS, see the FtsOptions reference <https://developer.android.com/reference/androidx/room/FtsOptions>

`UserDao.kt`

```kotlin
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
           "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}
```

`AppDatabase.kt`

``` kotlin
@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
```

### Usage

``` kotlin
val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
```

### Migrate

See <https://developer.android.com/training/data-storage/room/migrating-db-versions>
