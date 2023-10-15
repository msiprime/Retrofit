package com.example.retrofit.model

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/api/v1/university")
    fun getUniversities(): Call<University>
}