package com.example.afya.domain.usecase

import com.example.afya.data.model.Post
import com.example.afya.data.repository.PostRepository
import javax.inject.Inject

class AddPostUseCase @Inject constructor(private val postRepository: PostRepository) {

    suspend operator fun invoke(post: Post) = postRepository.addPost(post)

}