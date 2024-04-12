package com.example.praktik_zadanie3__penkov.viewmodel

import androidx.lifecycle.ViewModel
import com.example.praktik_zadanie3__penkov.repository.PostRepository
import com.example.praktik_zadanie3__penkov.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Int)=repository.likeById(id)
    fun shareById(id: Int)=repository.shareById(id)
}