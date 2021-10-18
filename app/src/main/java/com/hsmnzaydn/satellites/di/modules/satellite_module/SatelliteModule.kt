package com.hsmnzaydn.satellites.di.modules.satellite_module

import android.content.Context
import com.google.gson.Gson
import com.hsmnzaydn.satellites.features.satellite.data.repository.SatelliteRepositoryImpl
import com.hsmnzaydn.satellites.features.satellite.domain.repository.SatelliteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class SatelliteModule {

    @Provides
    @Singleton
    fun provideSatelliteRepository(@ApplicationContext context: Context,gson: Gson): SatelliteRepository {
        return SatelliteRepositoryImpl(context,gson)
    }

    @Provides
    @Singleton
    fun provideGson() = Gson()
}