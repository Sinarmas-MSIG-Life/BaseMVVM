package com.rifafauzi.basemvvm.ui.movies

import com.rifafauzi.basemvvm.base.BaseViewModel
import com.rifafauzi.basemvvm.repository.MoviesRepository
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */

class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : BaseViewModel()