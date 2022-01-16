package com.example.llegadasemt.network

import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://openapi.emtmadrid.es/v1/"

private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()

public interface Requests {
    @GET("{path}/")
    suspend fun getPath(@Path("path") path: String): DataResponse
}

public fun getRequest(): Requests {
    return retrofit.create(Requests::class.java)
}

data class DataResponse(
    @SerializedName("APIVersion") var api_version: JSONObject,
    @SerializedName("SourceCli") var source: String,
    @SerializedName("SourceIP") var sourceIP: String,
    @SerializedName("code") var status: String,
    @SerializedName("developerPortal") var portal: String,
    @SerializedName("instant") var date: String,
    @SerializedName("message") var text: String,
    @SerializedName("morehelp") var help: String,
    @SerializedName("poweredBy") var powered: String,
    @SerializedName("versions") var versions: List<String>
)