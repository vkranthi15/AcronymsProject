package com.acronymsapp.repository

import com.acronymsapp.repository.remote.model.Result

/**
 * Repository to communicate between different data sources.
 */
interface Repository {

    suspend fun fetchMeanings(shortForm: String): Result
}