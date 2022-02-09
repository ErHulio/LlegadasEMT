package com.example.llegadasemt.data

import com.google.gson.annotations.SerializedName

data class Hello (
    @SerializedName("APIVersion") var api_version: APIVersion,
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

data class APIVersion(
    @SerializedName("description") var description: String,
    @SerializedName("version") var version: String
)