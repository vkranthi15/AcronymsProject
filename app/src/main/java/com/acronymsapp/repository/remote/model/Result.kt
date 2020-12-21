package com.acronymsapp.repository.remote.model

import com.acronymsapp.repository.AcromineException

/**
 * Sealed class to handle Success and failure results
 */
sealed class Result {

    data class Success(val acromineList: List<Acromine>?): Result()
    data class Error(val exception: AcromineException): Result()
}



