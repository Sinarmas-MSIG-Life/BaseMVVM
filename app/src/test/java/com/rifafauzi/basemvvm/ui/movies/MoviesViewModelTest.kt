package com.rifafauzi.basemvvm.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rifafauzi.basemvvm.FakeData
import com.rifafauzi.basemvvm.common.Result
import com.rifafauzi.basemvvm.model.MovieResponse
import com.rifafauzi.basemvvm.model.Movies
import com.rifafauzi.basemvvm.repository.MoviesRepository
import com.rifafauzi.basemvvm.utils.RxImmediateSchedulerRule
import com.rifafauzi.basemvvm.utils.mock
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by rrifafauzikomara on 2020-01-20.
 */

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var repository: MoviesRepository

    @Mock
    lateinit var viewModel: MoviesViewModel

    private val moviesObs = mock<Observer<Result<List<Movies>>>>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun getListNowPlaying() {
        val data = FakeData.getDummyListMovies()
        val response = MovieResponse(
            listOf(
                Movies(
                    data[0].popularity,
                    data[0].vote_count,
                    data[0].poster_path,
                    data[0].idMovies,
                    data[0].backdrop_path,
                    data[0].title,
                    data[0].vote_average,
                    data[0].overview,
                    data[0].release_date
                )
            )
        )

        Mockito.`when`(repository.getListNowPlaying()).thenReturn(Observable.just(response))
        viewModel.getListNowPlaying()
        with(viewModel) {
            movies.observeForever(moviesObs)
            verify(repository).getListNowPlaying()
        }
    }
}