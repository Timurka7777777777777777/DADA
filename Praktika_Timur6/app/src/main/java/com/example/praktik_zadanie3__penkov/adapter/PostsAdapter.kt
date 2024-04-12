package com.example.praktik_zadanie3__penkov.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.praktik_zadanie3__penkov.R
import com.example.praktik_zadanie3__penkov.databinding.CardPostBinding
import com.example.praktik_zadanie3__penkov.repository.Post
import kotlin.math.ln
import kotlin.math.pow
typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit

class PostsAdapter(
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}
class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            osnovnoitext.text = post.content
            textlike.text = post.likes.toString()
            textShare.text = post.share.toString()
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_like_filled_24dp else R.drawable.ic_like_24dp
            )
            like.setOnClickListener {
                onLikeListener(post)
            }
            textlike.text = post.likes.toString()
            when {
                post.likes in 1000..999999 -> textlike.text = "${post.likes / 1000}K"
                post.likes < 1000 -> textlike.text = post.likes.toString()
                else -> textlike.text = String.format("%.1fM", post.likes.toDouble() / 1000000)
            }
            share.setOnClickListener {
                onShareListener(post)
            }
            textShare.text = post.share.toString()
            when {
                post.share < 1000 -> textShare.text = post.share.toString()
                post.share in 1000..999999 -> textShare.text = "${post.share / 1000}K"
                else -> textShare.text = String.format(
                    "%.1fM", post.share.toDouble() / 1000000
                )
            }
        }
    }
}

fun getFormatedNumber(count: Long): String {
    if (count < 1000) return "" + count
    val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
    return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "KMGTPE"[exp - 1])
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}