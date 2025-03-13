package com.afya.di

import com.afya.domain.repository.PostRepository
import com.afya.domain.usecase.AddPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAddPostUseCase(postRepository: PostRepository): AddPostUseCase {
        return AddPostUseCase(postRepository)
    }
    // ...existing code...
}
