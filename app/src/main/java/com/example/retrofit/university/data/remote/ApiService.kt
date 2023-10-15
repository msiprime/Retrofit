package com.example.retrofit.university.data.remote

import com.example.retrofit.university.data.model.University
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/api/v1/university")
    fun getUniversities(): Call<University>
}