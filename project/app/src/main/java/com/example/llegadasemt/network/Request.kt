package com.example.llegadasemt.network

import com.example.llegadasemt.data.Arrivals
import com.example.llegadasemt.data.Login
import com.example.llegadasemt.data.Logout
import com.example.llegadasemt.network.credentials.CLIENT_ID
import com.example.llegadasemt.network.credentials.PASS_KEY
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://openapi.emtmadrid.es/v2/"

private val retrofit = Retrofit.Builder().client(OkHttpClient.Builder().followRedirects(true).followSslRedirects(true).build()).addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()

//private const val CLIENT_ID = "<your_id>"
//private const val PASS_KEY = "<your_key>"

public interface Requests {
    @Headers(
        "X-ClientId: $CLIENT_ID",
        "passkey: $PASS_KEY"
    )
    @GET("mobilitylabs/user/login/")
    suspend fun login(): Login

    @GET("mobilitylabs/user/logout/")
    suspend fun logout(@Header("accessToken") token: String): Logout

    @Headers(
        "Content-Type: application/json"
    )
    @POST("transport/busemtmad/stops/{stop}/arrives/")
    suspend fun getArrivalTimes(@Header("accessToken") token: String, @Path("stop") stop: String, @Body body: ArriveBody): Arrivals
}

public fun getRequest(): Requests {
    return retrofit.create(Requests::class.java)
}