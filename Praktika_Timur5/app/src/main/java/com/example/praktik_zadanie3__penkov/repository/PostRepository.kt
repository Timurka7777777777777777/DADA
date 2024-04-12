package com.example.praktik_zadanie3__penkov.repository

import androidx.lifecycle.LiveData
import com.example.praktik_zadanie3__penkov.repository.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
}