package com.example.praktik_zadanie3__penkov.repository

import androidx.lifecycle.LiveData
import com.example.praktik_zadanie3__penkov.repository.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Int)
    fun shareById(id: Int)
    fun removeById(id: Int)
    fun save(post: Post)


}