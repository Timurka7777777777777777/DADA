package com.example.praktik_zadanie3__penkov.repository

    data class Post(
        val id: Int,
        val author: String,
        val content: String,
        val published: String,
        var likes: Int,
        var share: Int,
        var likedByMe: Boolean,
        val shareByMe: Boolean
    )