package com.rifafauzi.basemvvm.common

import androidx.annotation.StringRes

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */
 
sealed class Result <out T> {
    class NoInternetConnection<out T>: Result<T>()
    class Loading<out T>: Result<T>()
    class NoData<out T>: Result<T>()
    data class HasData<out T>(val data: T): Result<T>()
    data class Error<out T> (@StringRes val errorMessage: Int): Result<T>()
}