package com.example.divartask.presentation.places

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.divartask.databinding.PlacesItemLayoutBinding
import com.example.divartask.domain.model.PlacesDomain

class PlacesAdapter(
    private val onItemClicked: (id: Int) -> Unit
) : RecyclerView.Adapter<PlacesAdapter.ItemsViewHolder>() {
    private var cities: ArrayList<PlacesDomain>?= null

    fun setData(dataList: ArrayList<PlacesDomain>){
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
            onItemClicked(cities?.get(position)?.cityId ?: 0)
        }
    }

    inner class ItemsViewHolder(val binding: PlacesItemLayoutBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun onBind(data: PlacesDomain?) {
            binding.cityName.text =  data?.cityName
        }
    }
}