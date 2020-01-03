package com.rifafauzi.basemvvm.ui.movies


import androidx.fragment.app.Fragment
import com.rifafauzi.basemvvm.R
import com.rifafauzi.basemvvm.base.BaseFragment
import com.rifafauzi.basemvvm.databinding.FragmentMoviesBinding

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>() {

    override fun getViewModelClass(): Class<MoviesViewModel> = MoviesViewModel::class.java

    override fun getLayoutResourceId(): Int = R.layout.fragment_movies


}
