package com.example.spotify.di

import androidx.media3.extractor.ts.TsExtractor.Mode
import com.example.spotify.api.SpotifyAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jiosaavn-api-sand.vercel.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesSpotifyAPI(retrofit: Retrofit): SpotifyAPI {
        return retrofit.create(SpotifyAPI::class.java)
    }
}