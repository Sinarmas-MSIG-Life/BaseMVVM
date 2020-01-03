package com.rifafauzi.basemvvm.repository

import com.rifafauzi.basemvvm.api.ApiService
import com.rifafauzi.basemvvm.model.MovieResponse
import com.rifafauzi.basemvvm.utils.LANGUAGE
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */
 
class MoviesRepository @Inject constructor(private val apiService: ApiService) {

    fun getListNowPlaying() : Observable<MovieResponse> {
        return apiService.getListNowPlaying(LANGUAGE)
    }

}