package com.example.divartask.presentation.places

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.divartask.data.remote.entity.PlacesListData
import com.example.divartask.databinding.PlacesItemLayoutBinding

class PlacesAdapter(
    private val onItemClicked: (id: Int) -> Unit
) : RecyclerView.Adapter<PlacesAdapter.ItemsViewHolder>() {
    private var cities: ArrayList<PlacesListData.City>?= null

    fun setData(dataList: ArrayList<PlacesListData.City>?){
        cities = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder =
            ItemsViewHolder(
                PlacesItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

    override fun getItemCount(): Int = cities?.size ?:0

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.onBind(cities?.get(position))
        holder.itemView.setOnClickListener {
            onItemClicked(cities?.get(position)?.id ?: 0)
        }
    }

    inner class ItemsViewHolder(val binding: PlacesItemLayoutBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun onBind(data: PlacesListData.City?) {
            binding.cityName.text =  data?.name
        }
    }
}