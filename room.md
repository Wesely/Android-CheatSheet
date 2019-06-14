# Room : Android Database

## Reference

- Official  Guide <https://developer.android.com/training/data-storage/room>
- 7 Pro-tips for Room <https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1>

## Dependencies

> Releas notes : <https://developer.android.com/jetpack/androidx/releases/room>

``` gradle
    // at the top of gradle file
    apply plugin: 'kotlin-kapt'

    def room_version = "2.1.0-rc01"

    kapt "androidx.room:room-compiler:$room_version"

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

- Dao with RxJava <https://medium.com/androiddevelopers/room-rxjava-acb0cd4f3757>

`AppDatabase.kt`

``` kotlin
@Database(entities = [Address::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?:
        Room.databaseBuilder(context, AppDatabase::class.java, "MyDatabaseName")
            // .allowMainThreadQueries()
            .build().also { INSTANCE = it }
    }
}
```

- The access to `INSTANCE` should follow `Singleton` policy, because the cost of each database is expensive.

### Usage

``` kotlin
val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
```

### Migrate

See <https://developer.android.com/training/data-storage/room/migrating-db-versions>

``` java
@Database(entities = {Song.class}, version = 6)
public abstract class SongDatabase extends RoomDatabase {
    public abstract Song.SongDao songDao();

    private static SongDatabase INSTANCE;

    static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Log.d("SongDB", "version 5 -> 6, Migrated");
            database.execSQL("ALTER TABLE 'song' ADD COLUMN 'id' TEXT DEFAULT ''");
        }
    };
    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Log.d("SongDB", "version 4 -> 5, Migrated");
            database.execSQL("ALTER TABLE 'song' ADD COLUMN 'highestRecord_easy' INTEGER NOT NULL DEFAULT 0");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Log.d("SongDB", "version 3 -> 4, Migrated");
            database.execSQL("ALTER TABLE 'song' ADD COLUMN 'longestCombo' INTEGER NOT NULL DEFAULT 0");
        }
    };
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Log.d("SongDB", "version 2 -> 3, Migrated");
        }
    };

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {
                database.execSQL("ALTER TABLE 'song' ADD COLUMN 'highestRecord' INTEGER NOT NULL DEFAULT 0");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("SongDB", "Migrated");
        }
    };

    public static SongDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SongDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SongDatabase.class, "song_database")
                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

```
