package com.acronymsapp.dagger

import com.acronymsapp.repository.Repository
import com.acronymsapp.repository.RepositoryImpl
import com.acronymsapp.repository.remote.Constants
import com.acronymsapp.repository.remote.RemoteSource
import com.acronymsapp.repository.remote.RemoteSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AcromineModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteSource =
        RemoteSourceImpl.create(Constants.BASE_URL)

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteSource): Repository =
        RepositoryImpl(remoteDataSource)
}