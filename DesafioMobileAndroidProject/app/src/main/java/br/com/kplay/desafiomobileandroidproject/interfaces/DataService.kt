package br.com.kplay.desafiomobileandroidproject.interfaces

import br.com.kplay.desafiomobileandroidproject.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface DataService {

    @GET("data.json")
    fun list(): Call<List<Product>>

}