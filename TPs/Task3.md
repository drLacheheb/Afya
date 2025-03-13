# Task 3: Understanding Dependency Injection and Implementing Add Post Feature

## Dependency Injection with Dagger Hilt

The Afya app now uses Dagger Hilt for dependency injection across all layers of the application. Here's how it's structured:

### 1. Data Layer
- Repository implementations are provided via Hilt modules
- Data sources and their dependencies are injected into repositories
- Example: `PostRepository` and `DrugRepository` are injected with their dependencies

### 2. Domain Layer
- Use cases are injected with their required repositories
- Examples:
  - `GetPostsUseCase` is injected with `PostRepository`
  - `AddPostUseCase` is injected with `PostRepository`
  - `GetDrugsUseCase` is injected with `DrugRepository`

### 3. Presentation Layer
- ViewModels are annotated with `@HiltViewModel`
- Use cases are injected into ViewModels
- Example: `PostViewModel` is injected with `GetPostsUseCase`

## Your Task: Implement Add Post Feature

### 1. Create Add Post View
Create a new composable function `AddPostScreen` in the presentation layer with the following requirements:

- Input fields for:
  - Post title (Arabic)
  - Drug name (Arabic)
  - Post content (Arabic)
  - Location (Arabic)
  - Image URL (optional)
  - Post type (dropdown selection from `PostType` enum)
- Validation for all fields
- Submit button
- Success/Error feedback

### 2. Enhance AddPostViewModel

Update the `AddPostViewModel` to include the provided `Post` model and the following state and event classes:

```kotlin
// State for form fields
data class AddPostState(
    val title: String = "",
    val drugName: String = "",
    val content: String = "",
    val location: String = "",
    val imageUrl: String? = null,
    val postType: PostType = PostType.OFFER, // Default value
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

// Events that can be triggered
sealed class AddPostEvent {
    data class TitleChanged(val title: String) : AddPostEvent()
    data class DrugNameChanged(val drugName: String) : AddPostEvent()
    data class ContentChanged(val content: String) : AddPostEvent()
    data class LocationChanged(val location: String) : AddPostEvent()
    data class ImageUrlChanged(val url: String) : AddPostEvent()
    data class PostTypeSelected(val postType: PostType) : AddPostEvent()
    object Submit : AddPostEvent()
}
```

Required functionality:
1. State management for form fields
2. Input validation
3. Error handling
4. Integration with `AddPostUseCase`
5. Success/failure feedback

### Implementation Steps

1. **AddPostViewModel Implementation**
    - Add state management using `StateFlow`
    - Implement event handling
    - Add validation logic (e.g., non-empty fields for title, drug name, content, location)
    - Integrate with `AddPostUseCase` (call the use case when submitting the form)
    - Handle success/error states and UI feedback

2. **AddPostScreen Implementation**
    - Create UI components using Jetpack Compose
    - Connect UI events to ViewModel (e.g., text fields, dropdown, submit button)
    - Show loading state while submitting
    - Display error messages (e.g., "All fields are required")
    - Show success confirmation (e.g., "Post added successfully")

3. **Navigation Integration**
    - Add navigation to Add Post screen (already integrated in bottom navigation)
    - Handle back navigation
    - Pass results back to previous screen (e.g., refresh posts list)

### Success Criteria

Your implementation should:
1. Successfully add new posts to the repository
2. Validate all input fields (title, drug name, content, location)
3. Show appropriate loading states
4. Handle and display errors (e.g., invalid input, network errors)
5. Provide success feedback after a post is added
6. Follow MVVM architecture patterns
7. Properly utilize dependency injection
8. Use the provided `Post` model for form submission

### Tips
- Use `viewModelScope` for coroutines in ViewModel
- Implement proper error handling (e.g., network failures, validation errors)
- Follow Material Design guidelines for the UI
- Use proper state management with `StateFlow`
- Make use of Hilt's dependency injection for repositories and use cases
- Ensure proper Arabic text handling (right-to-left layout)

### Bonus Challenges
1. Add image upload functionality instead of URL input
2. Implement form state persistence (e.g., retain data on screen rotation)
3. Add unit tests for ViewModel logic
4. Add UI tests for the Add Post screen
5. Implement real-time validation (e.g., show errors as users type)
6. Add a map integration to select location coordinates

### Add Post UI Design

The Add Post screen should follow the app's existing design language:
- Use the Afya theme colors (e.g., primary color for buttons)
- Maintain right-to-left (RTL) layout for Arabic text
- Follow the existing card and input field styles
- Use consistent typography and spacing
- Include a dropdown for selecting `PostType` (e.g., OTC, Prescription)


Good luck with implementing the Add Post feature!
