package com.example.retrofit.model

data class UniversityItem(
    val coordinates: Coordinates,
    val _id: String,
    val universityName: String,
    val isActive: Boolean,
    val address: String,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
    val coverImg: String
)