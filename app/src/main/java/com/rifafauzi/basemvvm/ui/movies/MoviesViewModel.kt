package com.rifafauzi.basemvvm.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifafauzi.basemvvm.R
import com.rifafauzi.basemvvm.base.BaseResponse
import com.rifafauzi.basemvvm.base.BaseViewModel
import com.rifafauzi.basemvvm.common.Result
import com.rifafauzi.basemvvm.model.MovieResponse
import com.rifafauzi.basemvvm.model.Movies
import com.rifafauzi.basemvvm.repository.MoviesRepository
import com.rifafauzi.basemvvm.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */

class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : BaseViewModel() {

    private val _movies = MutableLiveData<Result<List<Movies>>>()
    val movies: LiveData<Result<List<Movies>>>
    get() = _movies

    fun getListNowPlaying() {
        mCompositeDisposable += repository.getListNowPlaying()
            .map { transformData(it) }
            .doOnSubscribe { setResultListNowPlaying(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Movies>>() {
                override fun onSuccess(response: List<Movies>) {
                    if (response.isEmpty()) {
                        setResultListNowPlaying(Result.NoData())
                        return
                    }
                    setResultListNowPlaying(Result.HasData(response))
                }

                override fun onNoInternetConnection() {
                    setResultListNowPlaying(Result.NoInternetConnection())
                }

                override fun onTimeout() {
                    setResultListNowPlaying(Result.Error(R.string.timeout))
                }

                override fun onUnknownError(message: String) {
                    setResultListNowPlaying(Result.Error(R.string.unknown_error))
                }

            })
    }

    private fun transformData(data: MovieResponse) : List<Movies> {
        val movies = mutableListOf<Movies>()
        for (i in data.results) {
            movies.add(
                Movies(
                    i.popularity,
                    i.vote_count,
                    i.poster_path,
                    i.idMovies,
                    i.backdrop_path,
                    i.title,
                    i.vote_average,
                    i.overview,
                    i.release_date
                )
            )
        }
        return movies.toList()
    }

    private fun setResultListNowPlaying(result: Result<List<Movies>>) {
        _movies.postValue(result)
    }

}