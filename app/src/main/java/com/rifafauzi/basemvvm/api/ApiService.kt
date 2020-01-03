package com.rifafauzi.basemvvm.api

import com.rifafauzi.basemvvm.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */

interface ApiService {

    @GET("now_playing")
    fun getListNowPlaying(@Query("language") language: String) : Observable<MovieResponse>

}