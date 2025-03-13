package com.afya.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.afya.domain.model.PostType
import com.afya.presentation.viewmodel.AddPostViewModel

@Composable
fun AddPostScreen(navController: NavController, viewModel: AddPostViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = state.title,
            onValueChange = { viewModel.onEvent(AddPostEvent.TitleChanged(it)) },
            label = { Text("Post Title (Arabic)") },
            modifier = Modifier.fillMaxWidth()
        )
        // ...existing code for other input fields...
        DropdownMenu(
            expanded = true,
            onDismissRequest = { /*TODO*/ }
        ) {
            PostType.values().forEach { postType ->
                DropdownMenuItem(onClick = { viewModel.onEvent(AddPostEvent.PostTypeSelected(postType)) }) {
                    Text(postType.name)
                }
            }
        }
        Button(
            onClick = { viewModel.onEvent(AddPostEvent.Submit) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        if (state.error != null) {
            Text(state.error, color = MaterialTheme.colors.error)
        }
        if (state.isSuccess) {
            Text("Post added successfully", color = MaterialTheme.colors.primary)
        }
    }
}
