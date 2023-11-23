package com.example.divartask.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.divartask.data.entity.PostsData
import com.example.divartask.databinding.PostViewItemBinding
import com.example.divartask.databinding.ProgressbarLayoutBinding
import com.example.divartask.databinding.SubtitleViewItemBinding
import com.example.divartask.databinding.TitleViewItemBinding
import com.example.divartask.presentation.util.WidgetTypeEnum


const val VIEW_TYPE_TITLE = 0
const val VIEW_TYPE_SUBTITLE = 1
const val VIEW_TYPE_POST = 2
const val VIEW_TYPE_LOADING = 3

class PostsAdapter(
    private val onItemClicked: (token: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var postList: ArrayList<PostsData.Post?> = arrayListOf()
    fun setData(list: ArrayList<PostsData.Post?>){
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
                PostViewItemBinding.inflate(
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
        private val binding: PostViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(post: PostsData.Post?) {
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
        fun onBind(post: PostsData.Post?) {
            binding.textTitle.text = post?.data?.text
        }
    }

    inner class SubTitleViewHolder(
        private val binding: SubtitleViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(post: PostsData.Post?) {
            binding.textSubTitle.text = post?.data?.text
        }
    }
}

class RoomListDiffUtil : DiffUtil.ItemCallback<PostsData.Post?>() {
    override fun areItemsTheSame(oldItem: PostsData.Post, newItem: PostsData.Post): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: PostsData.Post, newItem: PostsData.Post): Boolean =
        oldItem.data?.token == newItem.data?.token

}