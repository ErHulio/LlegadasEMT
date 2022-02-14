package com.example.llegadasemt.network

class ArriveBody (language: String, stopYN: String, estimationYN: String, indicesYN: String, dateTimeYN: String) {
    val cultureInfo: String = language
    val Text_StopRequired_YN: String = stopYN
    val Text_EstimationsRequired_YN: String = estimationYN
    val Text_IndicesRequired_YN: String = indicesYN
    val DateTime_Referenced_Indices_YYYYMMDD: String = dateTimeYN
}