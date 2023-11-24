package com.example.divartask.presentation.widgets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.divartask.data.remote.entity.Widgets
import com.example.divartask.databinding.ProgressbarLayoutBinding
import com.example.divartask.databinding.SubtitleViewItemBinding
import com.example.divartask.databinding.TitleViewItemBinding
import com.example.divartask.databinding.WidgetViewItemBinding
import com.example.divartask.presentation.util.WidgetTypeEnum


const val VIEW_TYPE_TITLE = 0
const val VIEW_TYPE_SUBTITLE = 1
const val VIEW_TYPE_POST = 2
const val VIEW_TYPE_LOADING = 3

class PostsAdapter(
    private val onItemClicked: (token: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var postList: ArrayList<Widgets.Widget?> = arrayListOf()
    fun setData(list: ArrayList<Widgets.Widget?>){
        val oldSize = postList.size
        postList.addAll(list)
        notifyItemRangeChanged(oldSize,list.size)
    }

    fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            if (!postList.contains(null)) {
                postList.add(null)
            }
        } else {
            postList.remove(null)
        }
        notifyItemChanged(findLoadingIndex())
    }

    private fun findLoadingIndex(): Int {
        return postList.indexOf(null)
    }

    override fun getItemViewType(position: Int): Int {
        return postList[position]?.run {
            when(this.widgetType){
                WidgetTypeEnum.POST_ROW.name -> VIEW_TYPE_POST
                WidgetTypeEnum.SUBTITLE_ROW.name -> VIEW_TYPE_SUBTITLE
                WidgetTypeEnum.TITLE_ROW.name -> VIEW_TYPE_TITLE
                else -> VIEW_TYPE_TITLE
            }
        }?:run {
           VIEW_TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_TITLE -> TitleViewHolder(
                TitleViewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            VIEW_TYPE_SUBTITLE -> SubTitleViewHolder(
                SubtitleViewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            VIEW_TYPE_POST -> PostViewHolder(
                WidgetViewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_LOADING ->  LoadingHolder(
                ProgressbarLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> {
                TitleViewHolder(
                    TitleViewItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PostViewHolder -> {
                holder.onBind(postList[position])
                holder.itemView.setOnClickListener {
                    onItemClicked.invoke(postList[position]?.data?.token ?: "")
                }
            }
            is TitleViewHolder -> {holder.onBind(postList[position])}
            is SubTitleViewHolder -> {holder.onBind(postList[position])}
        }
    }

    override fun getItemCount(): Int = postList.size

    inner class LoadingHolder(val binding: ProgressbarLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
    inner class PostViewHolder(
        private val binding: WidgetViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(post: Widgets.Widget?) {
            binding.title.text = post?.data?.title
            binding.price.text = post?.data?.price
            binding.district.text = post?.data?.district

            Glide.with(binding.root.context)
                .load(post?.data?.thumbnail)
                .into(binding.logo)
        }
    }

    inner class TitleViewHolder(
        private val binding: TitleViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(post: Widgets.Widget?) {
            binding.textTitle.text = post?.data?.text
        }
    }

    inner class SubTitleViewHolder(
        private val binding: SubtitleViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(post: Widgets.Widget?) {
            binding.textSubTitle.text = post?.data?.text
        }
    }
}

class RoomListDiffUtil : DiffUtil.ItemCallback<Widgets.Widget?>() {
    override fun areItemsTheSame(oldItem: Widgets.Widget, newItem: Widgets.Widget): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Widgets.Widget, newItem: Widgets.Widget): Boolean =
        oldItem.data?.token == newItem.data?.token

}