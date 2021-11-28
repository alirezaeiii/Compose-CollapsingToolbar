package com.android.sample.core.di

import android.content.Context
import androidx.room.Room
import com.android.sample.core.BuildConfig
import com.android.sample.core.database.AppDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideDisneyDao(db: AppDatabase) = db.disneyDao()
}