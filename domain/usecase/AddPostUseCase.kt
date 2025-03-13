package com.afya.domain.usecase

import com.afya.domain.repository.PostRepository
import javax.inject.Inject

class AddPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(
        title: String,
        drugName: String,
        content: String,
        location: String,
        imageUrl: String?,
        postType: PostType
    ): Result {
        // ...existing code for adding post...
    }
}
