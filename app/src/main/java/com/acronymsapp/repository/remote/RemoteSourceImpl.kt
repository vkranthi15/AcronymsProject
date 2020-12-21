package com.acronymsapp.repository.remote

import com.acronymsapp.repository.remote.api.AcromineService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Remote data source implementation to build retrofit instance and retrieve response from apis
 */
class RemoteSourceImpl(private val acromineService: AcromineService): RemoteSource {

    companion object {

        fun create(baseURL: String): RemoteSource {
            // Initialization of Retrofit instance
            val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(AcromineService::class.java)
            return RemoteSourceImpl(service)
        }
    }

    override suspend fun fetchMeanings(shortForm: String) = acromineService.fetchMeanings(shortForm)
}