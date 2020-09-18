package br.com.kplay.desafiomobileandroidproject.dao

import br.com.kplay.desafiomobileandroidproject.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){

    companion object {

        private lateinit var retrofit: Retrofit
        private val baseUrl = Constants.URL_BASE

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()

            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

        fun <T> createService(serviceClass: Class<T>): T {
            return getRetrofitInstance().create(serviceClass)
        }

    }

}