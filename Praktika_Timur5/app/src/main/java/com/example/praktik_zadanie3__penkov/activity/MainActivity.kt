package com.example.praktik_zadanie3__penkov.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.praktik_zadanie3__penkov.R
import com.example.praktik_zadanie3__penkov.databinding.ActivityMainBinding
import com.example.praktik_zadanie3__penkov.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                osnovnoitext.text = post.content
                textlike.text = post.likes.toString()
                textShare.text = post.share.toString()
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_like_filled_24dp else R.drawable.ic_like_24dp
                )
                textlike.text = post.likes.toString()
                when {
                    post.likes in 1000..999999 -> textlike.text = "${post.likes / 1000}K"
                    post.likes < 1000 -> textlike.text = post.likes.toString()
                    else -> textlike.text = String.format("%.1fM", post.likes.toDouble() / 1000000)
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
            binding.like.setOnClickListener {
                viewModel.like()
            }
            binding.share.setOnClickListener {
                viewModel.share()
            }
        }
    }
}
