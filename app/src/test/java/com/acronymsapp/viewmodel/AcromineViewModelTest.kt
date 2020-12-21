package com.acronymsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.acronymsapp.TestCoroutineRule
import com.acronymsapp.repository.Repository
import com.acronymsapp.repository.remote.model.Acromine
import com.acronymsapp.repository.remote.model.LongForm
import com.acronymsapp.repository.remote.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

@ExperimentalCoroutinesApi
class AcromineViewModelTest {

    @get:Rule
    internal val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = TestCoroutineRule()

    private var repository = mock(Repository::class.java)
    private lateinit var viewModel: AcromineViewModel
    private lateinit var mockObserver: Observer<Result>


    @Before
    fun setUp() {
        viewModel = AcromineViewModel(repository)
        mockObserver = mock(Observer::class.java) as Observer<Result>
    }

    @Test
    fun `fetch meanings`() = coroutineTestRule.runBlockingTest {
        viewModel.fetchMeanings(anyString()).observeForever(mockObserver)
        val longForm = LongForm("Food and Drug Administration", 3345, 1966, listOf())
        val acromine = Acromine("fda", listOf(longForm))
        val responseList = listOf(acromine)

        Mockito.`when`(repository.fetchMeanings(anyString()))
            .thenReturn(Result.Success(responseList))

        Mockito.verify(mockObserver, times(1)).onChanged(any<Result>())
    }
}