package com.mashup.kkyuni.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private val YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/"
    private val BASE_API_URL = "http://3.37.106.181:8080/api/v1/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("movie_log_client")
    fun provideMovieLogOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor {
                it.proceed(
                    it.request().newBuilder().addHeader(
                        "Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0U3ViIiwiaWF0IjoxNjM1MzQwMTA5LCJleHAiOjE2NjEyNjAxMDl9.7P_6TEytA2gtFYfXMJX-IqNbjzUCfKGYsfBkDlVnlOY"
                    ).build()
                )
            }.build()
    }

    @Named("youtube_api")
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(YOUTUBE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Named("kkyuni_api")
    @Provides
    @Singleton
    fun provideKKyuniRetrofit(@Named("movie_log_client") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
}