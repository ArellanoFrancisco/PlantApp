package com.example.plantapp

import com.example.plantapp.Model.Remote.RetrofitFlowers
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestRetrofitFlowers {

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testRetrofit() {

        val expectedBaseUrl = mockWebServer.url("/").toString()

        val retrofit = Retrofit.Builder()
            .baseUrl(expectedBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        RetrofitFlowers.retrofitInstance = retrofit
        val retrofitInstace = RetrofitFlowers.retrofitInstance
        Assert.assertEquals(expectedBaseUrl, retrofitInstace.baseUrl().toString())

    }
}