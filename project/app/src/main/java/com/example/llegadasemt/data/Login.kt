package com.example.llegadasemt.data

import com.google.gson.annotations.SerializedName

data class Login (
    @SerializedName("code") var status: String,
    @SerializedName("description") var description: String,
    @SerializedName("datetime") var date: String,
    @SerializedName("data") var data: List<LoginData>
)

data class LoginData (
    @SerializedName("updatedAt") var updateTime: String,
    @SerializedName("username") var user: String,
    @SerializedName("accessToken") var token: String,
    @SerializedName("email") var email: String,
    @SerializedName("idUser") var id: String,
    @SerializedName("tokenSecExpiration") var tokenExpiration: Int,
    @SerializedName("apiCounter") var status: APICounterData,
    @SerializedName("nameApp") var app: String,
    @SerializedName("priv") var privacy: String
)

data class APICounterData (
    @SerializedName("current") var nCurrent: Int,
    @SerializedName("dailyUse") var nUses: Int,
    @SerializedName("owner") var nOwners: Int,
    @SerializedName("licenseUse") var license: String
)