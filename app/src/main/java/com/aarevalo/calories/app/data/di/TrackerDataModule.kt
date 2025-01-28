package com.aarevalo.calories.app.data.di

import com.aarevalo.calories.app.data.remote.api.OpenFoodApi
import com.aarevalo.calories.app.data.remote.repository.DefaultTrackerRepository
import com.aarevalo.calories.app.domain.tracker.repository.TrackerRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    private fun getMoshi() : Moshi{
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    @Provides
    @Singleton
    fun provideOpenFoodApi(
        client: OkHttpClient
    ): OpenFoodApi {
        return Retrofit.Builder()
            .baseUrl(OpenFoodApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(
        api: OpenFoodApi
    ): TrackerRepository {
        return DefaultTrackerRepository(
            api = api
        )
    }
}