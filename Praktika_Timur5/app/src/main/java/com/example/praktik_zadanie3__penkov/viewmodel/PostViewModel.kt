package com.example.praktik_zadanie3__penkov.viewmodel

import androidx.lifecycle.ViewModel
import com.example.praktik_zadanie3__penkov.repository.PostRepository
import com.example.praktik_zadanie3__penkov.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like()=repository.like()
    fun share()=repository.share()
}