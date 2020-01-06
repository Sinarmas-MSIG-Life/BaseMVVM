package com.rifafauzi.basemvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rifafauzi.basemvvm.databinding.MoviesItemBinding
import com.rifafauzi.basemvvm.model.Movies

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */
 
class MoviesAdapter (private val listener: OnMoviesPressedListener) : ListAdapter<Movies, MoviesAdapter.ViewHolder>(
    DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MoviesItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), listener, holder.adapterPosition)

    class ViewHolder(private val binding: MoviesItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Movies, listener: OnMoviesPressedListener, position: Int) {
            binding.data = model
            binding.root.setOnClickListener {
                listener.onMoviesPressed(model, position)
            }
            binding.executePendingBindings()
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Movies>(){
            override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnMoviesPressedListener {
        fun onMoviesPressed(movies: Movies, position: Int)
    }

}