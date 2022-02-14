package com.example.llegadasemt.data

import com.google.gson.annotations.SerializedName

data class Logout (
    @SerializedName("code") var status: String,
    @SerializedName("description") var description: String,
    @SerializedName("datetime") var date: String,
    @SerializedName("data") var data: List<String>
)