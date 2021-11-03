package com.android.sample.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.sample.common.util.Resource
import com.android.sample.common.util.schedulers.TestSchedulerProvider
import com.android.sample.core.database.section.SectionDao
import com.android.sample.core.response.Section
import com.android.sample.app.viewmodel.SectionViewModel
import io.reactivex.Observable
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class SectionViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var service: ApiService

    @Mock
    private lateinit var dao: SectionDao

    private lateinit var schedulerProvider: TestSchedulerProvider
    private lateinit var sectionRepository: SectionRepository
    private lateinit var viewModel: SectionViewModel

    @Before
    fun setUp() {
        // Make the sure that all schedulers are immediate.
        schedulerProvider = TestSchedulerProvider()
        sectionRepository = SectionRepository(service, dao)

        val link = Link("id", "title", "href")
        viewModel = SectionViewModel(sectionRepository, schedulerProvider, link)
    }

    @Test
    fun givenDaoReturnCache_whenGetResult_thenReturnSuccessFromCache() {
        val section = Section("sectionId", "title", "description")
        `when`(dao.getSection(anyString())).thenReturn(section.asDatabaseModel())
        `when`(service.getSection(anyString())).thenReturn(Observable.error(Exception("")))

        viewModel.liveData.value.let {
            assertThat(it, `is`(Resource.Loading))
        }

        schedulerProvider.testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)

        viewModel.liveData.value.let {
            assertThat(it, `is`(notNullValue()))
            if (it is Resource.Success) {
                it.data?.let { data ->
                    assertTrue(data.sectionId == "sectionId")
                    assertTrue(data.title == "title")
                    assertTrue(data.description == "description")
                }
            } else {
                fail("Wrong type $it")
            }
        }
    }

    @Test
    fun givenDaoReturnNull_whenGetResult_thenReturnErrorWithNullMessage() {
        val section = Section("sectionId", "title", "description")
        `when`(dao.getSection(anyString())).thenReturn(null)
        `when`(service.getSection(anyString())).thenReturn(Observable.just(section))

        viewModel.liveData.value.let {
            assertThat(it, `is`(Resource.Loading))
        }

        schedulerProvider.testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)

        viewModel.liveData.value.let {
            assertThat(it, `is`(notNullValue()))
            if (it is Resource.Error) {
                assertThat(it.message, `is`(nullValue()))
            } else {
                fail("Wrong type $it")
            }
        }
    }

    @Test
    fun givenServerReturnError_whenGetResult_thenReturnErrorWithMessage() {
        `when`(service.getSection(anyString())).thenReturn(Observable.error(Exception("error")))

        viewModel.liveData.value.let {
            assertThat(it, `is`(Resource.Loading))
        }

        schedulerProvider.testScheduler.advanceTimeBy(1, TimeUnit.MILLISECONDS)

        viewModel.liveData.value.let {
            assertThat(it, `is`(notNullValue()))
            if (it is Resource.Error) {
                assertThat(it.message, `is`(notNullValue()))
                assertThat(it.message, `is`("error"))
            } else {
                fail("Wrong type $it")
            }
        }
    }
}