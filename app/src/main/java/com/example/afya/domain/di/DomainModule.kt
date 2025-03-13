package com.example.afya.domain.di

import com.example.afya.data.repository.DrugRepository
import com.example.afya.data.repository.PostRepository
import com.example.afya.domain.usecase.AddPostUseCase
import com.example.afya.domain.usecase.GetDrugsUseCase
import com.example.afya.domain.usecase.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetPostsUseCase(postRepository: PostRepository): GetPostsUseCase {
        return GetPostsUseCase(postRepository)
    }

    @Provides
    @Singleton
    fun provideGetDrugsUseCase(drugRepository: DrugRepository): GetDrugsUseCase {
        return GetDrugsUseCase(drugRepository)
    }

    @Provides
    @Singleton
    fun provideAddPostUseCase(postRepository: PostRepository): AddPostUseCase {
        return AddPostUseCase(postRepository)
    }
}