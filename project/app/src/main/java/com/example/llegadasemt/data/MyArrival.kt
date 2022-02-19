package com.example.llegadasemt.data

data class MyArrival (
    val line: String,
    val timeInt: Int,
    val estimatedTime: String,
    val nextEstimatedTime: String,
    val destination: String,
    val bgColor: String,
    val fgColor: String
)