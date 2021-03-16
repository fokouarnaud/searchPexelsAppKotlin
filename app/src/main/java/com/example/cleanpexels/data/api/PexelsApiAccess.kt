package com.example.cleanpexels.data.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class PexelsApiAccess {
    companion object {

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .protocols(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1, Protocol.SPDY_3))
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .addInterceptor { chain: Interceptor.Chain ->
                val newRequest = chain.request().newBuilder()
                    .build()

                val response = chain.proceed(newRequest)
                Log.e("TAG"," data "+response.body()?.string())
                response.close()

                return@addInterceptor chain.proceed(newRequest)
            }
            .build()

        fun getApiService(): PexelsApiService {
            return Retrofit.Builder()
                .baseUrl(PexelsApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PexelsApiService::class.java)
        }
    }
}