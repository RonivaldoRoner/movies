package com.ronivaldoroner.movies.providers.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ronivaldoroner.movies.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.util.concurrent.TimeUnit


object Retrofit {

    private const val DEFAULT_TIMEOUT_SECONDS = 30L

    private val client = OkHttpClient.Builder().build()

    fun <T> createWebService(service: Class<T>): T =
        makeRetrofit(baseURL()).create(service)

    private fun makeRetrofit(url: String, vararg interceptors: Interceptor): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .client(makeHttpClient(interceptors))
            .addConverterFactory(GsonConverterFactory.create(makeConverter()))
            .build()

    private fun makeHttpClient(interceptors: Array<out Interceptor>): OkHttpClient =
        client.newBuilder()
            .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(headersInterceptor())
            .addInterceptor(queryInterceptor())
            .apply { interceptors().addAll(interceptors) }
            .addInterceptor(loggingInterceptor())
            .build()


    private fun loggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    private fun headersInterceptor(): Interceptor =
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Accept-Language", "pt-br")
                    .addHeader("Content-Type", "application/json")
                    .build()
            )
        }

    private fun queryInterceptor(): Interceptor =
        Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(API_KEY, BuildConfig.apiKey)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

    private fun makeConverter(): Gson =
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .setDateFormat(DateFormat.FULL)
            .create()

    private fun baseURL(): String = BuildConfig.baseUrl


    private const val API_KEY = "apikey"
}