package com.example.llegadasemt.data

import com.google.gson.annotations.SerializedName

data class Arrivals (
    @SerializedName("code") var status: String,
    @SerializedName("description") var description: String,
    @SerializedName("datetime") var date: String,
    @SerializedName("data") var data: List<ArriveData>
)

data class ArriveData (
    @SerializedName("Arrive") var arrivals: List<Arrive>,
    @SerializedName("StopInfo") var stopData: List<StopData>,
    @SerializedName("ExtraInfo") var description: List<Object>,
    @SerializedName("Incident") var date: Object,
)

data class Arrive (
    @SerializedName("line") var line: String,
    @SerializedName("stop") var stopNumber: String,
    @SerializedName("isHead") var isHead: String,
    @SerializedName("destination") var destination: String,
    @SerializedName("deviation") var deviation: Int,
    @SerializedName("bus") var busNumber: Int,
    @SerializedName("geometry") var location: Location,
    @SerializedName("estimateArrive") var estimatedTime: Int,
    @SerializedName("DistanceBus") var distance: Int,
    @SerializedName("positionTypeBus") var positionType: String
)
data class Location (
    @SerializedName("type") var type: String,
    @SerializedName("coordinates") var coordinates: List<Double>
)

data class StopData (
    @SerializedName("lines") var lines: List<Line>,
    @SerializedName("stopId") var id: String,
    @SerializedName("stopName") var stopName: String,
    @SerializedName("geometry") var location: Location,
    @SerializedName("Direction") var address: String,
)

data class Line (
    @SerializedName("label") var label: String,
    @SerializedName("line") var line: String,
    @SerializedName("nameA") var endA: String,
    @SerializedName("nameB") var endB: String,
    @SerializedName("metersFromHeader") var distance: Int,
    @SerializedName("to") var destination: String,
    @SerializedName("color") var background: String
)