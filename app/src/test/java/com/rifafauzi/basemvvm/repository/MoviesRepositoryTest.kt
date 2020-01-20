package com.rifafauzi.basemvvm.repository

import com.rifafauzi.basemvvm.FakeData
import com.rifafauzi.basemvvm.model.MovieResponse
import io.reactivex.Observable
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by rrifafauzikomara on 2020-01-20.
 */

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryTest {

    @Mock
    lateinit var repository: MoviesRepository

    private val data = FakeData.getDummyListMovies()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = mock(MoviesRepository::class.java)
    }

    @Test
    fun checkNullListMovies() {
        val list = repository.getListNowPlaying()
        Assert.assertNull(list)
    }

    @Test
    fun getListNowPlaying() {
        val list = com.rifafauzi.basemvvm.utils.mock<Observable<MovieResponse>>()
        Mockito.`when`(repository.getListNowPlaying()).thenReturn(list)

        val result = repository.getListNowPlaying()
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$list] must be matches on each other!",
            result, CoreMatchers.`is`(list)
        )
    }
}