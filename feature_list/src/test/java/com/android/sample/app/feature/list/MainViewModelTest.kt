package com.android.sample.app.feature.list

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.sample.app.feature.list.ui.MainViewModel
import com.android.sample.common.util.Resource
import com.android.sample.core.database.DisneyDao
import com.android.sample.core.network.DisneyService
import com.android.sample.core.repository.DisneyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var api: DisneyService

    @Mock
    private lateinit var dao: DisneyDao

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            `when`(api.fetchDisneyPosterList()).thenReturn(emptyList())
            `when`(dao.getPosters()).thenReturn(emptyList())
        }

        val repository = DisneyRepository(dao, api, context, Dispatchers.Main)

        testCoroutineRule.pauseDispatcher()
        val viewModel = MainViewModel(repository)
        assertThat(viewModel.stateFlow.value, `is`(Resource.Loading))
        testCoroutineRule.resumeDispatcher()
        assertThat(viewModel.stateFlow.value, `is`(Resource.Success(emptyList())))
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)

        testCoroutineRule.runBlockingTest {
            `when`(api.fetchDisneyPosterList()).thenThrow(RuntimeException(""))
            `when`(dao.getPosters()).thenReturn(emptyList())
        }
        val repository = DisneyRepository(dao, api, context, Dispatchers.Main)

        testCoroutineRule.pauseDispatcher()
        val viewModel = MainViewModel(repository)
        assertThat(viewModel.stateFlow.value, `is`(Resource.Loading))
        testCoroutineRule.resumeDispatcher()
        assertThat(viewModel.stateFlow.value, `is`(Resource.Error(errorMsg)))
    }
}