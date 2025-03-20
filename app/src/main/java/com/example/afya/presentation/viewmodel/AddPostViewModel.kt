package com.example.afya.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afya.domain.usecase.AddPostUseCase
import com.example.afya.domain.model.PostType
import com.example.afya.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(private val addPostUseCase: AddPostUseCase) : ViewModel() {

    private val _state = MutableStateFlow(AddPostState())
    val state: StateFlow<AddPostState> = _state

    fun onEvent(event: AddPostEvent) {
        when (event) {
            is AddPostEvent.TitleChanged -> _state.value = _state.value.copy(title = event.title)
            is AddPostEvent.DrugNameChanged -> _state.value = _state.value.copy(drugName = event.drugName)
            is AddPostEvent.ContentChanged -> _state.value = _state.value.copy(content = event.content)
            is AddPostEvent.LocationChanged -> _state.value = _state.value.copy(location = event.location)
            is AddPostEvent.ImageUrlChanged -> _state.value = _state.value.copy(imageUrl = event.url)
            is AddPostEvent.PostTypeSelected -> _state.value = _state.value.copy(postType = event.postType)
            is AddPostEvent.Submit -> submitPost()
        }
    }

    private fun submitPost() {
        val currentState = _state.value

        if (currentState.title.isBlank() || currentState.drugName.isBlank() ||
            currentState.content.isBlank() || currentState.location.isBlank()) {
            _state.value = _state.value.copy(error = "يجب ملء جميع الحقول")
            return
        }

        _state.value = _state.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            try {
                val post = Post(
                    title = currentState.title,
                    drugName = currentState.drugName,
                    content = currentState.content,
                    location = currentState.location,
                    imageUrl = currentState.imageUrl,
                    postType = currentState.postType
                )

                addPostUseCase(post)

                _state.value = _state.value.copy(isSuccess = true, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = "حدث خطأ أثناء الإرسال", isLoading = false)
            }
        }
    }
}

data class AddPostState(
    val title: String = "",
    val drugName: String = "",
    val content: String = "",
    val location: String = "",
    val imageUrl: String? = null,
    val postType: PostType = PostType.OFFER,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

sealed class AddPostEvent {
    data class TitleChanged(val title: String) : AddPostEvent()
    data class DrugNameChanged(val drugName: String) : AddPostEvent()
    data class ContentChanged(val content: String) : AddPostEvent()
    data class LocationChanged(val location: String) : AddPostEvent()
    data class ImageUrlChanged(val url: String) : AddPostEvent()
    data class PostTypeSelected(val postType: PostType) : AddPostEvent()
    object Submit : AddPostEvent()
}
