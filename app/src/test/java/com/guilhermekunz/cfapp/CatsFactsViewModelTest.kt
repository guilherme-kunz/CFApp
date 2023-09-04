package com.guilhermekunz.cfapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.guilhermekunz.cfapp.api.repository.Repository
import com.guilhermekunz.cfapp.api.response.FactsResponse
import com.guilhermekunz.cfapp.api.response.NetworkResponse
import com.guilhermekunz.cfapp.ui.CatsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.any
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

class CatsFactsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var repository: Repository

    @Mock
    lateinit var loadingStateLiveData: MutableLiveData<CatsViewModel.State>

    @Mock
    lateinit var catsFactsResponseLiveData: MutableLiveData<FactsResponse>

    @Mock
    lateinit var errorLiveData: MutableLiveData<Unit>

    @Mock
    lateinit var observer: Observer<CatsViewModel.State>

    private lateinit var viewModel: CatsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = CatsViewModel(repository)
        viewModel.loadingStateLiveData = loadingStateLiveData
        viewModel.catsFactsResponseLiveData = catsFactsResponseLiveData
        viewModel.errorLiveData = errorLiveData
    }

    @Test
    fun `getCatsFacts() should update loading state and fetch data successfully`() {
        // Arrange
        val fakeResponse = CatsFactsResponse(/* Fake data here */)
        val successResponse = NetworkResponse.Success(fakeResponse)
        Mockito.`when`(repository.getCatsFacts()).thenReturn(successResponse)

        // Act
        viewModel.getCatsFacts()

        // Assert
        Mockito.verify(loadingStateLiveData, times(2)).value = any(Thread.State::class.java)
        Mockito.verify(catsFactsResponseLiveData).value = fakeResponse
        Mockito.verifyNoMoreInteractions(loadingStateLiveData, errorLiveData)
    }

    @Test
    fun `getCatsFacts() should update loading state and handle error`() {
        // Arrange
        val errorResponse = NetworkResponse.Failed("Error message")
        Mockito.`when`(repository.getCatsFacts()).thenReturn(errorResponse)

        // Act
        viewModel.getCatsFacts()

        // Assert
        Mockito.verify(loadingStateLiveData, times(2)).value = any(Thread.State::class.java)
        Mockito.verify(errorLiveData).value = Unit
        Mockito.verifyNoMoreInteractions(loadingStateLiveData, catsFactsResponseLiveData)
    }

    @After
    fun teardown() {
        // Cleanup
        coroutineTestRule.testDispatcher.cleanupTestCoroutines()
    }
}