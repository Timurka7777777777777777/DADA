package com.example.praktik_zadanie3__penkov.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.praktik_zadanie3__penkov.R
import com.example.praktik_zadanie3__penkov.adapter.PostsAdapter
import com.example.praktik_zadanie3__penkov.databinding.ActivityMainBinding
import com.example.praktik_zadanie3__penkov.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter({
            viewModel.likeById(it.id)},
            {
                viewModel.shareById(it.id)
            })
        binding.list.adapter=adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}