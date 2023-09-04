package com.guilhermekunz.cfapp.ui

import androidx.lifecycle.Observer
import com.guilhermekunz.cfapp.api.repository.Repository
import com.guilhermekunz.cfapp.api.response.FactsResponse
import com.guilhermekunz.cfapp.api.response.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class CatsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var factsObserver: Observer<FactsResponse?>

    @Mock
    private lateinit var errorObserver: Observer<Unit>

    private lateinit var viewModel: CatsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = CatsViewModel(repository)
        viewModel.catsFactsResponse.observeForever(factsObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `test getCatsFacts success`() = runBlocking {
        val mockResponse = FactsResponse(/* Your mock data here */)
        Mockito.`when`(repository.getCatsFacts()).thenReturn(NetworkResponse.Success(mockResponse))

        viewModel.getCatsFacts()

        Mockito.verify(factsObserver).onChanged(mockResponse)
    }

    @Test
    fun `test getCatsFacts failure`() = runBlocking {
        Mockito.`when`(repository.getCatsFacts()).thenReturn(NetworkResponse.Failed)

        viewModel.getCatsFacts()

        Mockito.verify(errorObserver).onChanged(Unit)
    }
}