package com.example.digitoon.presentation.places

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitoon.data.remote.entity.MoviesData
import com.example.digitoon.databinding.MoviesItemLayoutBinding
import com.example.digitoon.presentation.util.loadImageToView

class MoviesAdapter(
    private val onItemClicked: (id: String) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ItemsViewHolder>() {
    private var movies: List<MoviesData.Search>? = null

    fun setData(dataList: List<MoviesData.Search>){
        movies = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder =
            ItemsViewHolder(
                MoviesItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

    override fun getItemCount(): Int = movies?.size ?:0

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.onBind(movies?.get(position))
        holder.itemView.setOnClickListener {
            onItemClicked(movies?.get(position)?.imdbID ?: "")
        }
    }

    inner class ItemsViewHolder(val binding: MoviesItemLayoutBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun onBind(data: MoviesData.Search?) {
            binding.logo.loadImageToView(data?.poster?:"")
            binding.title.text = data?.title
            binding.type.text = data?.type
            binding.year.text = data?.year
        }
    }
}