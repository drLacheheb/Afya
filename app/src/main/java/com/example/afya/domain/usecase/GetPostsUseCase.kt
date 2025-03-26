package com.example.afya.domain.usecase

import com.example.afya.data.repository.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postRepository: PostRepository) {

    operator fun invoke() = postRepository.getPosts()
}