package com.example.retrofitdemo.models

data class ApiDemoData(
    val base: String,
    val end_date: String,
    val rates: Rates,
    val start_date: String,
    val success: Boolean,
    val timeseries: Boolean
)