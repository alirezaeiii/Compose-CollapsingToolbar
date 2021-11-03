package com.android.sample.core.di

import com.android.sample.common.base.BaseListRepository
import com.android.sample.core.repository.DisneyRepository
import com.android.sample.core.response.Poster
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideDisneyRepository(repository: DisneyRepository): BaseListRepository<Poster>
}