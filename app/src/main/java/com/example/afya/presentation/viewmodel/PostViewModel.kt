package com.example.afya.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afya.data.model.Post
import com.example.afya.data.repository.PostRepositoryImpl
import com.example.afya.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UIState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class PostViewModel @Inject constructor(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {
   
 
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init{
        loadPosts()
    }


    fun loadPosts() {
     
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {

            getPostsUseCase().catch {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    posts = emptyList(),
                    error = "Failed to load posts"
                )
            }.collect{ posts ->

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    posts = posts,
                    error = null
                )
            }
        }
    }
}

