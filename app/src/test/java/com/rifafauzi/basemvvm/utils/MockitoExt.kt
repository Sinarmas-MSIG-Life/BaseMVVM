package com.rifafauzi.basemvvm.utils

import org.mockito.Mockito

/**
 * Created by rrifafauzikomara on 2020-01-20.
 */
 
inline fun <reified T> mock(): T = Mockito.mock(T::class.java)