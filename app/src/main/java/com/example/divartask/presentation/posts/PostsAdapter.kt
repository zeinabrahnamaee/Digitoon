package com.example.divartask.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.divartask.data.entity.PostsData
import com.example.divartask.databinding.PostViewItemBinding
import com.example.divartask.databinding.SubtitleViewItemBinding
import com.example.divartask.databinding.TitleViewItemBinding
import com.example.divartask.presentation.util.WidgetTypeEnum


const val VIEW_TYPE_TITLE = 0
const val VIEW_TYPE_SUBTITLE = 1
const val VIEW_TYPE_POST = 2

class PostsAdapter(
    private val onItemClicked: (token: String) -> Unit
) : ListAdapter<PostsData.Post, RecyclerView.ViewHolder>(RoomListDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).widgetType) {
            WidgetTypeEnum.POST_ROW.name -> VIEW_TYPE_POST
            WidgetTypeEnum.SUBTITLE_ROW.name -> VIEW_TYPE_SUBTITLE
            WidgetTypeEnum.TITLE_ROW.name -> VIEW_TYPE_TITLE
            else -> {
                VIEW_TYPE_TITLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> TitleViewHolder(
                TitleViewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            1 -> SubTitleViewHolder(
                SubtitleViewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            2 -> PostViewHolder(
                PostViewItemBinding.inflate(
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
                holder.onBind(currentList[position])
                holder.itemView.setOnClickListener {
                    onItemClicked.invoke(currentList[position].data?.token ?: "")
                }
            }
            is TitleViewHolder -> {holder.onBind(currentList[position])}
            is SubTitleViewHolder -> {holder.onBind(currentList[position])}
        }
    }

    override fun getItemCount(): Int = currentList.size

    inner class PostViewHolder(
        private val binding: PostViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(post: PostsData.Post) {
            binding.title.text = post.data?.title
            binding.price.text = post.data?.price
            binding.district.text = post.data?.district

            Glide.with(binding.root.context)
                .load(post.data?.thumbnail)
                .into(binding.logo)
        }
    }

    inner class TitleViewHolder(
        private val binding: TitleViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(post: PostsData.Post) {
            binding.textTitle.text = post.data?.text
        }
    }

    inner class SubTitleViewHolder(
        private val binding: SubtitleViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(post: PostsData.Post) {
            binding.textSubTitle.text = post.data?.text
        }
    }
}

class RoomListDiffUtil : DiffUtil.ItemCallback<PostsData.Post>() {
    override fun areItemsTheSame(oldItem: PostsData.Post, newItem: PostsData.Post): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: PostsData.Post, newItem: PostsData.Post): Boolean =
        oldItem.data?.token == newItem.data?.token

}