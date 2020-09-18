package com.mykuya.android.data.network

import androidx.lifecycle.LiveData
import com.mykuya.android.BuildConfig
import com.mykuya.android.data.HomeDataModel
import com.mykuya.android.model.ResultModel
import com.mykuya.android.model.network.Resource
import com.mykuya.android.utils.AuthenticationInterceptor
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier


interface ApiService {

    @GET("/api/v1/getHomeData")
    fun getHomeData():LiveData<Resource<ResultModel<HomeDataModel>>>


    companion object Factory {

        private val BASE_URL = BuildConfig.BASE_URL

        private val httpClient =  OkHttpClient.Builder()

        private val builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())

        private var retrofit = builder.build()


        fun getApiService() : ApiService {
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.readTimeout(30, TimeUnit.SECONDS)
            httpClient.writeTimeout(30, TimeUnit.SECONDS)
            httpClient.hostnameVerifier((HostnameVerifier { _, _ -> true }))
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.apply {
                    logging.level = HttpLoggingInterceptor.Level.BODY
                }
                httpClient.interceptors().add(logging)
            }
            builder.client(httpClient.build())
            retrofit = builder.build()
            return retrofit.create(ApiService::class.java)
        }

        fun getApiService(auth:String) : ApiService {
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.readTimeout(30, TimeUnit.SECONDS)
            httpClient.writeTimeout(30, TimeUnit.SECONDS)
            val interceptor = AuthenticationInterceptor(auth)
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.apply {
                    logging.level = HttpLoggingInterceptor.Level.BODY
                }
                httpClient.interceptors().add(logging)
            }
            httpClient.hostnameVerifier((HostnameVerifier { _, _ -> true }))
            if (!httpClient.interceptors().contains(interceptor)){
                httpClient.addInterceptor(interceptor)

                builder.client(httpClient.build())
                retrofit = builder.build()
            }
            return retrofit.create(ApiService::class.java);
        }
    }

}
