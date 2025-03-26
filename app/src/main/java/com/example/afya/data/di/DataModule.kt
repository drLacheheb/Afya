package com.example.afya.data.di

import com.example.afya.data.repository.DrugRepository
import com.example.afya.data.repository.DrugRepositoryImpl
import com.example.afya.data.repository.PostRepository
import com.example.afya.data.repository.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providePostRepository(): PostRepository {
        return PostRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideDrugRepository(): DrugRepository{
        return DrugRepositoryImpl()
    }

}