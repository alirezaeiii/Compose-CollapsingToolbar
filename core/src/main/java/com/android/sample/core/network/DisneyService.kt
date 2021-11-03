package com.android.sample.core.network

import com.android.sample.core.response.Poster
import retrofit2.http.GET

interface DisneyService {

    @GET("DisneyPosters2.json")
    suspend fun fetchDisneyPosterList(): List<Poster>
}