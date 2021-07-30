package com.example.newapp.lib.network.di

import com.example.newapp.lib.core.injection.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named

@Module
object RetrofitModule {

    @Provides
    @ApplicationScope
    fun provideRetrofit(
        converterFactory: Converter.Factory,
        @Named("string") stringConverterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("base/url")
            .client(okHttpClient)
            .addConverterFactory(stringConverterFactory)
            .addConverterFactory(converterFactory)
            .build()
    }
}
