package com.acronymsapp.repository.remote.api

import com.acronymsapp.repository.remote.model.Acromine
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API service for retrieving acronyms
 */
interface AcromineService {

    @GET("software/acromine/dictionary.py")
    suspend fun fetchMeanings(@Query("sf") shortForm: String): Response<List<Acromine>>
}