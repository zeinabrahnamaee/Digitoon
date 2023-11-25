package com.example.divartask.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.divartask.databinding.DetailDescriptionItemviewBinding
import com.example.divartask.databinding.DetailHeaderItemviewBinding
import com.example.divartask.databinding.DetailImageSliderItemBinding
import com.example.divartask.databinding.DetailInfoItemviewBinding
import com.example.divartask.databinding.DetailSliderItemviewBinding
import com.example.divartask.databinding.DetailTitleItemviewBinding
import com.example.divartask.domain.model.DetailDomain
import com.example.divartask.presentation.util.DetailWidgetTypeEnum


const val IMAGE_SLIDER_ROW = 0
const val HEADER_ROW = 1
const val INFO_ROW = 2
const val TITLE_ROW = 3
const val DESCRIPTION_ROW = 4

class DetailAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var widgetList: ArrayList<DetailDomain>

    fun setData(list: ArrayList<DetailDomain>){
        widgetList = list
    }

    override fun getItemViewType(position: Int): Int {
        return when (widgetList[position].widgetType) {
            DetailWidgetTypeEnum.IMAGE_SLIDER_ROW.name -> IMAGE_SLIDER_ROW
            DetailWidgetTypeEnum.DESCRIPTION_ROW.name -> DESCRIPTION_ROW
            DetailWidgetTypeEnum.TITLE_ROW.name -> TITLE_ROW
            DetailWidgetTypeEnum.INFO_ROW.name -> INFO_ROW
            DetailWidgetTypeEnum.HEADER_ROW.name -> HEADER_ROW
            else -> {
                TITLE_ROW
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> SliderViewHolder(
                DetailSliderItemviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            1 -> HeaderViewHolder(
                DetailHeaderItemviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            2 -> InfoViewHolder(
                DetailInfoItemviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            3 -> TitleViewHolder(
                DetailTitleItemviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            4 -> DescriptionViewHolder(
                DetailDescriptionItemviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> {
                TitleViewHolder(
                DetailTitleItemviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
           is SliderViewHolder -> {holder.onBind(widgetList[position])}
           is TitleViewHolder -> {holder.onBind(widgetList[position])}
           is HeaderViewHolder -> {holder.onBind(widgetList[position])}
           is DescriptionViewHolder -> {holder.onBind(widgetList[position])}
           is InfoViewHolder -> {holder.onBind(widgetList[position])}
        }
    }

    override fun getItemCount(): Int = widgetList.size

    inner class SliderViewHolder(
        private val binding: DetailSliderItemviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(widget: DetailDomain) {
            binding.imageSlider.setImages(widget.items)
        }
    }
        inner class TitleViewHolder(
            private val binding: DetailTitleItemviewBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            fun onBind(widget: DetailDomain) {
                binding.title.text = widget.text

            }
        }

        inner class DescriptionViewHolder(
            private val binding: DetailDescriptionItemviewBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            fun onBind(widget: DetailDomain) {
                binding.description.text = widget.text
            }
        }

        inner class HeaderViewHolder(
            private val binding: DetailHeaderItemviewBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            fun onBind(widget: DetailDomain) {
                binding.title.text = widget.title
                binding.subTitle.text = widget.subtitle
            }
        }

        inner class InfoViewHolder(
            private val binding: DetailInfoItemviewBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            fun onBind(widget: DetailDomain) {
                binding.title.text = widget.title
                binding.value.text = widget.value
            }
        }
}