package com.example.aizat.course_work.data

import com.example.aizat.course_work.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

object ApiFactory {

    private val classToService: MutableMap<KClass<*>, Any> = HashMap()

    val gson: Gson by lazy {
        GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("${BuildConfig.API_BASE_URL}")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttp)
                .build()
    }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor {
                    val request = it.request()

                    val token = TokenStorage.getToken()

                    if (token != null) {
                        it.proceed(request.newBuilder().header("my_token", "$token").build())
                    } else {
                        it.proceed(request)
                    }
                }
                .addInterceptor {
                    val response = it.proceed(it.request())

                    if (response.code() in 200..299) {
                        return@addInterceptor response
                    }
                    response
                }
                .build()
    }

    fun <T : Any> getRetrofitService(retrofitServiceClass: KClass<T>): T {
        @Suppress("UNCHECKED_CAST")
        var service = classToService[retrofitServiceClass] as T?

        if (service != null) {
            return service
        }

        service = retrofit.create(retrofitServiceClass.java)
        classToService[retrofitServiceClass] = service

        return service
    }

}