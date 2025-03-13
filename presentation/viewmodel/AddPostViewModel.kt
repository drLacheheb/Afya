package com.afya.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afya.domain.model.PostType
import com.afya.domain.usecase.AddPostUseCase
import com.afya.presentation.AddPostEvent
import com.afya.presentation.AddPostState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val addPostUseCase: AddPostUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddPostState())
    val state: StateFlow<AddPostState> = _state

    fun onEvent(event: AddPostEvent) {
        when (event) {
            is AddPostEvent.TitleChanged -> {
                _state.value = _state.value.copy(title = event.title)
            }
            // ...existing code for other events...
            AddPostEvent.Submit -> {
                submitPost()
            }
        }
    }

    private fun submitPost() {
        val currentState = _state.value
        if (currentState.title.isBlank() || currentState.drugName.isBlank() ||
            currentState.content.isBlank() || currentState.location.isBlank()) {
            _state.value = _state.value.copy(error = "All fields are required")
            return
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val result = addPostUseCase(
                title = currentState.title,
                drugName = currentState.drugName,
                content = currentState.content,
                location = currentState.location,
                imageUrl = currentState.imageUrl,
                postType = currentState.postType
            )
            _state.value = _state.value.copy(isLoading = false)
            if (result.isSuccess) {
                _state.value = _state.value.copy(isSuccess = true)
            } else {
                _state.value = _state.value.copy(error = result.error)
            }
        }
    }
}
