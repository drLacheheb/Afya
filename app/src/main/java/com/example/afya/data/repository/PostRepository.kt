package com.example.afya.data.repository

import com.example.afya.data.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPosts(): Flow<List<Post>>

    suspend fun addPost(post: Post)

}
