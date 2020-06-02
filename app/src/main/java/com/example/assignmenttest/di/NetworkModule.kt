package com.example.assignmenttest.di

import com.example.assignmenttest.BuildConfig
import com.example.assignmenttest.util.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val MODULE_NAME = "Network Module"
val networkModule =Kodein.Module(MODULE_NAME,false){
  bind<OkHttpClient>() with singleton { getOkhttpClient() }
  bind<Retrofit>() with singleton { getRetrofit(instance()) }
    bind<ApiService>() with singleton { getApiService(instance()) }
}

fun getOkhttpClient(): OkHttpClient {
    val httpBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpBuilder.interceptors().add(httpLoggingInterceptor)
    return httpBuilder.build()
}

fun getRetrofit(okHttpClient: OkHttpClient): Retrofit =Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

private fun getApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
