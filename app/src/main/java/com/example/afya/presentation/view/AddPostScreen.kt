import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Model
enum class PostType { OFFER, REQUEST }

data class Post(
    val title: String,
    val drugName: String,
    val content: String,
    val location: String,
    val imageUrl: String?,
    val postType: PostType
)

// State
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

// Event
sealed class AddPostEvent {
    data class TitleChanged(val title: String) : AddPostEvent()
    data class DrugNameChanged(val drugName: String) : AddPostEvent()
    data class ContentChanged(val content: String) : AddPostEvent()
    data class LocationChanged(val location: String) : AddPostEvent()
    data class ImageUrlChanged(val url: String) : AddPostEvent()
    data class PostTypeSelected(val postType: PostType) : AddPostEvent()
    object Submit : AddPostEvent()
}

// UseCase
class AddPostUseCase @Inject constructor(private val repository: PostRepository) {
    suspend fun execute(post: Post): Result<Unit> {
        return repository.addPost(post)
    }
}

// Repository
interface PostRepository {
    suspend fun addPost(post: Post): Result<Unit>
}

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
            currentState.content.isBlank() || currentState.location.isBlank()
        ) {
            _state.value = currentState.copy(error = "All fields are required")
            return
        }

        _state.value = currentState.copy(isLoading = true)
        viewModelScope.launch {
            val result = addPostUseCase.execute(
                Post(
                    currentState.title,
                    currentState.drugName,
                    currentState.content,
                    currentState.location,
                    currentState.imageUrl,
                    currentState.postType
                )
            )
            _state.value = if (result.isSuccess) {
                AddPostState(isSuccess = true)
            } else {
                currentState.copy(isLoading = false, error = "Failed to add post")
            }
        }
    }
}