package com.example.llegadasemt.network

import com.example.llegadasemt.data.Hello
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://openapi.emtmadrid.es/v1/"

private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()

public interface Requests {
    @GET("{path}/")
    suspend fun getPath(@Path("path") path: String): Hello
}

public fun getRequest(): Requests {
    return retrofit.create(Requests::class.java)
}