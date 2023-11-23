package com.example.divartask.presentation.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.divartask.databinding.DetailImageSliderItemBinding
import com.example.divartask.databinding.DetailImageSliderLayoutBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailImageSlider : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding =
        DetailImageSliderLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    fun setImages(images: List<String?>) {
        binding.apply {
            val pagerAdapter =
                DetailImagesSliderAdapter(images)
            imagePager.adapter = pagerAdapter

            TabLayoutMediator(pagerIndicator, imagePager)
            { tab, position -> }.attach()
        }
    }
}

class DetailImagesSliderAdapter(private val dataList: List<String?>) :
    RecyclerView.Adapter<DetailImagesSliderAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            DetailImageSliderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    inner class Holder(private val binding: DetailImageSliderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: String?) {
            image?.let { binding.imageView.loadImageToView(it) }
        }
    }
}