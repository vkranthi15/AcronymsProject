package com.acronymsapp.repository

import com.acronymsapp.repository.remote.RemoteSource
import com.acronymsapp.repository.remote.model.Result
import java.lang.Exception

/**
 * Repository implementation to communicate to remote source and fetch result
 */
class RepositoryImpl(
    private val remoteSource: RemoteSource
) : Repository {

    override suspend fun fetchMeanings(shortForm: String): Result {
        val response = remoteSource.fetchMeanings(shortForm)
        return if (response.isSuccessful) {
            Result.Success(response.body())
        } else {
            Result.Error(AcromineException(response.errorBody()?.string()))
        }
    }
}

data class AcromineException(
    val errorMessage: String?
) : Exception()