# Task 2: Implement the First UI in view/MainScreen

## Code Explanation

The codebase is structured around the Afya application using **MVVM Architecture**. Here are the key components:

1. **PostViewModel**: Handles the business logic related to posts.
2. **MainActivity**: Acts as the entry point of the application.
3. **MainScreen**: Composable that displays the UI, including the list of posts.
4. **PostRepository**: Acts as a repository for posts.
5. **DrugRepository**: Manages drug data.
6. **Post Model & Drug Model**: Define the data structure for posts and drugs.

## Task

Your task is to implement the first UI in `view/MainScreen` based on the design provided in `FirstUI.jpg`. The UI should include:

- A list of posts.
- A navigation bar.
- A post detail view when a post is selected.
- A search bar to filter posts by name or blood type.

### Additional Requirements

1. **Add More Posts and Drugs**:
   - Add at least 5 more posts to the mock data in `PostRepository.kt` and more drugs on `DrugRepository.kt`.
   - Ensure the new posts and drugs are displayed in the UI.

2. **Implement Search Functionality**:
   - Add a search bar to the top of the `view/MainScreen` UI.
   - A navigation bar.
   - Implement functionality to filter the posts list based on the search query (e.g., by post name or drug name).

## Instructions

1. Open `view/MainScreen.kt`.
2. Implement the UI layout in `view/MainScreen`.
3. Use the `PostViewModel` to fetch and display posts.
4. Ensure the UI matches the design in `FirstUI.jpg`.

## FirstUI

![First UI](First_UI.png)

Good luck!
