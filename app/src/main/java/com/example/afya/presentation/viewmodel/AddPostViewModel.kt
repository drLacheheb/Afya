package com.example.afya.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.afya.domain.usecase.AddPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddPostViewModel @Inject constructor(private val addPostUseCase: AddPostUseCase) : ViewModel() {

    //TODO: complete you logic
}