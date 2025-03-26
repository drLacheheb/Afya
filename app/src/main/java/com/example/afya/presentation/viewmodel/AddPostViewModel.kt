package com.example.afya.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afya.domain.usecase.AddPostUseCase
import com.example.afya.data.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
            is AddPostEvent.TitleChanged -> _state.value = _state.value.copy(title = event.title)
            is AddPostEvent.DrugNameChanged -> _state.value = _state.value.copy(drugName = event.drugName)
            is AddPostEvent.ContentChanged -> _state.value = _state.value.copy(content = event.content)
            is AddPostEvent.LocationChanged -> _state.value = _state.value.copy(location = event.location)
            is AddPostEvent.ImageUrlChanged -> _state.value = _state.value.copy(imageUrl = event.url) // ✅ إرجاع imageUrl
            is AddPostEvent.PostTypeSelected -> _state.value = _state.value.copy(postType = event.postType)
            is AddPostEvent.Submit -> submitPost()
            else -> {}
        }
    }

    private fun submitPost() {
        val currentState = _state.value
        if (currentState.title.isBlank() || currentState.drugName.isBlank() || currentState.content.isBlank() || currentState.location.isBlank()) {
            _state.value = _state.value.copy(error = "يجب ملء جميع الحقول")
            return
        }

        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                addPostUseCase(
                    Post(
                        title = currentState.title,
                        drugName = currentState.drugName,
                        content = currentState.content,
                        location = currentState.location,
                        image = currentState.imageUrl, // ✅ التأكد من تمرير imageUrl
                        postType = currentState.postType,
                        id = TODO(),
                        createdAt = TODO(),
                        updatedAt = TODO(),
                        expiredAt = TODO()
                    )
                )
                _state.value = _state.value.copy(isSuccess = true, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = "حدث خطأ أثناء الإرسال", isLoading = false)
            }
        }
    }
}