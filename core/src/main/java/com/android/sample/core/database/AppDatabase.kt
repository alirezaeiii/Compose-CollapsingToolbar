package com.android.sample.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.sample.core.BuildConfig

@Database(
    entities = [DatabasePoster::class],
    version = BuildConfig.DATABASE_VERSION,
    exportSchema = BuildConfig.DATABASE_EXPORT_SCHEMA
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun disneyDao(): DisneyDao
}