package com.example.plantapp.Model.Remote

import com.example.plantapp.Model.Remote.FromInternet.DetailsFlower
import com.example.plantapp.Model.Remote.FromInternet.ListFlowers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FlowerApi {

    @GET("plantas")
    suspend fun fetchFlowersList(): Response<List<ListFlowers>>


    // seleccionar uno

    @GET("plantas/{id}")
    suspend fun fetchFlowersDetail(@Path("id") id: Int): Response<DetailsFlower>
}