package com.example.praktik_zadanie3__penkov.activity

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
            }
        })
        binding.list.adapter=adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        viewModel.edited.observe(this){ post->
            if(post.id == 0){
                return@observe
            }
            with(binding.content){
                binding.group.visibility = View.VISIBLE
                requestFocus()
                setText(post.content)
            }
        }
        binding.cancel.setOnClickListener {
            with(binding.content){
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                binding.group.visibility = View.GONE
            }
        }
        binding.save.setOnClickListener {
            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text.toString())
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }

        }
    }
}



