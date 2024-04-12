package com.example.praktik_zadanie3__penkov.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.praktik_zadanie3__penkov.repository.Post
import com.example.praktik_zadanie3__penkov.repository.PostRepository
import com.example.praktik_zadanie3__penkov.repository.PostRepositoryInMemoryImpl
import com.example.praktik_zadanie3__penkov.repository.PostRepositorySharedPrefsImpl

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    published = "",
    shareByMe = false,
    likes = 0,
    share = 0
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository =  PostRepositorySharedPrefsImpl(application)
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }
    fun edit(post:Post){
        edited.value = post
    }
    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }
    fun likeById(id: Int)=repository.likeById(id)
    fun shareById(id: Int)=repository.shareById(id)
    fun removeById(id: Int) = repository.removeById(id)
}