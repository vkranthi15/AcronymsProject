package com.acronymsapp.repository.remote

import com.acronymsapp.repository.remote.model.Acromine
import retrofit2.Response

/**
 * Remote data source
 */
interface RemoteSource {

    suspend fun fetchMeanings(shortForm: String): Response<List<Acromine>>
}