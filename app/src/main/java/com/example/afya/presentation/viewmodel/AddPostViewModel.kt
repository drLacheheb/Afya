package com.example.afya.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afya.data.model.Post
import com.example.afya.domain.usecase.AddPostUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import java.util.regex.Pattern
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.afya.data.repository.PostTypeRepository

@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val addPostUseCase: AddPostUseCase,
    private val postTypeRepository: PostTypeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddPostState())
    val state: StateFlow<AddPostState> = _state.asStateFlow()

    fun onEvent(event: AddPostEvent) {
        when (event) {
            is AddPostEvent.TitleChanged -> {
                _state.value = _state.value.copy(
                    title = event.title,
                    error = if (isArabic(event.title)) null else "عنوان المنشور يجب أن يكون بالعربية"
                )
            }
            is AddPostEvent.ContentChanged -> {
                _state.value = _state.value.copy(
                    content = event.content,
                    error = if (event.content.isNotBlank()) null else "المحتوى لا يجب أن يكون فارغًا"
                )
            }
            is AddPostEvent.PostTypeSelected -> {
                viewModelScope.launch {
                    postTypeRepository.getPostTypes().collect { types: List<PostType> ->
                        _state.value = _state.value.copy(
                            postType = event.postType,
                            error = if (types.any { it.name == event.postType }) null else "نوع المنشور غير صالح"
                        )
                    }
                }
            }
            is AddPostEvent.ImageUrlChanged -> {
                _state.value = _state.value.copy(
                    imageUrl = event.url,
                    error = if (isValidUrl(event.url)) null else "رابط الصورة غير صالح"
                )
            }
            is AddPostEvent.LocationChanged -> {
                _state.value = _state.value.copy(
                    location = event.location,
                    error = if (event.location.isNotBlank()) null else "يجب إدخال الموقع"
                )
            }
            is AddPostEvent.Submit -> {
                if (canSubmit()) {
                    addPost()
                } else {
                    _state.value = _state.value.copy(error = "يجب ملء جميع الحقول بشكل صحيح")
                }
            }
            is AddPostEvent.ImageUriChanged -> {
                _state.value = _state.value.copy(
                    imageUri = event.uri,
                    error = null
                )
            }
        }
    }

    private fun canSubmit(): Boolean {
        val currentState = _state.value
        return currentState.title.isNotBlank() &&
                isArabic(currentState.title) &&
                currentState.content.isNotBlank() &&
                currentState.postType.isNotBlank() &&
                currentState.location.isNotBlank()
    }

    private fun addPost() {
        val currentState = _state.value
        _state.value = currentState.copy(isLoading = true, error = null)

        viewModelScope.launch {
            try {
                val post = Post(
                    id = UUID.randomUUID().toString(),
                    title = currentState.title,
                    content = currentState.content,
                    postType = PostType(name = currentState.postType),
                    location = currentState.location,
                    imageUrl = currentState.imageUrl,
                    createdAt = Date(),
                    updatedAt = Date()
                )
                addPostUseCase.addPost(post)
                _state.value = currentState.copy(isSuccess = true, isLoading = false)
            } catch (e: Exception) {
                _state.value = currentState.copy(error = "فشل في إضافة المنشور", isLoading = false)
            }
        }
    }

    private fun isArabic(text: String): Boolean {
        return text.isNotEmpty() && Pattern.matches("^[\\u0600-\\u06FF\\s]+$", text)
    }

    private fun isValidUrl(url: String): Boolean {
        val regex = "^(https?|ftp)://[^\\s/$.?#].\\s*$".toRegex()
        return regex.matches(url)
    }

    sealed class AddPostEvent {
        data class TitleChanged(val title: String) : AddPostEvent()
        data class ContentChanged(val content: String) : AddPostEvent()
        data class PostTypeSelected(val postType: String) : AddPostEvent()
        data class LocationChanged(val location: String) : AddPostEvent()
        data class ImageUrlChanged(val url: String) : AddPostEvent()
        data class ImageUriChanged(val uri: Uri) : AddPostEvent()
        object Submit : AddPostEvent()
    }

    data class AddPostState(
        val title: String = "",
        val content: String = "",
        val postType: String = "",
        val location: String = "",
        val imageUrl: String = "",
        val imageUri: Uri = Uri.EMPTY,
        val isLoading: Boolean = false,
        val error: String? = null,
        val isSuccess: Boolean = false
    )
}