package com.acronymsapp.repository

import com.acronymsapp.repository.remote.RemoteSource
import com.acronymsapp.repository.remote.model.Acromine
import com.acronymsapp.repository.remote.model.LongForm
import com.acronymsapp.repository.remote.model.Result
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import retrofit2.Response

class RepositoryTest {

    private var remoteSource = Mockito.mock(RemoteSource::class.java)
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = RepositoryImpl(remoteSource)
    }

    @Test
    fun `fetch meanings success`() = runBlocking {
        val longForm = LongForm("Food and Drug Administration", 3345, 1966, listOf())
        val acromine = Acromine("fda", listOf(longForm))
        val responseList = listOf(acromine)

        Mockito.`when`(remoteSource.fetchMeanings(ArgumentMatchers.anyString()))
            .thenReturn(Response.success(responseList))

        val expectedResult: Result = repository.fetchMeanings(ArgumentMatchers.anyString())
        assertTrue(expectedResult is Result.Success)
        val expectedSuccessResult = expectedResult as Result.Success
        assertEquals(expectedSuccessResult.acromineList, responseList)
    }

    @Test
    fun `fetch meanings error`() = runBlocking {
        Mockito.`when`(remoteSource.fetchMeanings(ArgumentMatchers.anyString()))
            .thenReturn(Response.error(500, ResponseBody.create(null, "Server Down")))

        val expectedResult: Result = repository.fetchMeanings(ArgumentMatchers.anyString())
        assertTrue(expectedResult is Result.Error)

        val expectedErrorResult = expectedResult as Result.Error
        assertEquals(expectedErrorResult.exception.errorMessage, "Server Down")
    }
}