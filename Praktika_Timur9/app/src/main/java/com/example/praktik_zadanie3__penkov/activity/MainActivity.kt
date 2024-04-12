package com.example.praktik_zadanie3__penkov.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels

import com.example.praktik_zadanie3__penkov.R
import com.example.praktik_zadanie3__penkov.adapter.OnInteractionListener
import com.example.praktik_zadanie3__penkov.adapter.PostsAdapter
import com.example.praktik_zadanie3__penkov.databinding.ActivityMainBinding
import com.example.praktik_zadanie3__penkov.repository.AndroidUtils
import com.example.praktik_zadanie3__penkov.repository.Post
import com.example.praktik_zadanie3__penkov.viewmodel.PostViewModel
import com.google.ai.client.generativeai.type.content


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onVideo(post: Post) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=5mGdCO7kNF0"))
                startActivity(intent)
            }
            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }
            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }
            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }
        })
        binding.list.adapter=adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }
        viewModel.edited.observe(this){ post->
            if(post.id == 0){
                return@observe
            }
            newPostLauncher.launch(post.content)
        }
        binding.cancel.setOnClickListener {
            newPostLauncher.launch(null)

        }
        binding.fab.setOnClickListener {
            newPostLauncher.launch(null)

        }
        binding.fab.setOnClickListener{
            newPostLauncher.launch(null)
        }

    }

}





