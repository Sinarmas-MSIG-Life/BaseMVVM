package com.rifafauzi.basemvvm.model

import com.google.gson.annotations.SerializedName

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */
 
data class MovieResponse(
    @SerializedName("results")
    val results: List<Movies>
)