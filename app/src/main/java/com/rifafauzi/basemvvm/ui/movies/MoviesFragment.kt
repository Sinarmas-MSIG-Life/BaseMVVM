package com.rifafauzi.basemvvm.ui.movies


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.basemvvm.R
import com.rifafauzi.basemvvm.adapter.MoviesAdapter
import com.rifafauzi.basemvvm.base.BaseFragment
import com.rifafauzi.basemvvm.databinding.FragmentMoviesBinding
import com.rifafauzi.basemvvm.model.Movies
import com.rifafauzi.basemvvm.common.Result

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>(), MoviesAdapter.OnMoviesPressedListener {

    private val adapter = MoviesAdapter(this)

    override fun getViewModelClass(): Class<MoviesViewModel> = MoviesViewModel::class.java

    override fun getLayoutResourceId(): Int = R.layout.fragment_movies

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        vm.getListNowPlaying()

        vm.movies.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideLeagues()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showLeagues()
                        hideLoading()
                        refreshData(it.data)
                    }
                    is Result.NoData -> {
                        hideLeagues()
                        hideLoading()
                        snackBar(resources.getString(R.string.empty_data))
                    }
                    is Result.Error -> {
                        hideLeagues()
                        hideLoading()
                        snackBar(resources.getString(R.string.unknown_error))
                    }
                    is Result.NoInternetConnection -> {
                        hideLeagues()
                        hideLoading()
                        snackBar(resources.getString(R.string.no_connection))
                    }
                }
            }
        })
    }

    override fun onMoviesPressed(movies: Movies, position: Int) {
        snackBar(movies.title)
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvMovies.layoutManager = layoutManager
        binding.rvMovies.adapter = adapter
    }

    private fun refreshData(movies : List<Movies>) {
        adapter.submitList(movies)
    }

    private fun showLoading() {
        binding.showLoading = true
    }

    private fun hideLoading() {
        binding.showLoading = false
    }

    private fun showLeagues() {
        binding.showData = true
    }

    private fun hideLeagues() {
        binding.showData = false
    }

}
