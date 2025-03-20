package com.example.afya.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afya.presentation.viewmodel.AddPostViewModel
import com.example.afya.presentation.viewmodel.AddPostEvent

@Composable
fun AddPostScreen(
    navController: NavController,
    viewModel: AddPostViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = state.title,
            onValueChange = { viewModel.onEvent(AddPostEvent.TitleChanged(it)) },
            label = { Text("عنوان المنشور") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        TextField(
            value = state.drugName,
            onValueChange = { viewModel.onEvent(AddPostEvent.DrugNameChanged(it)) },
            label = { Text("اسم الدواء") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        TextField(
            value = state.content,
            onValueChange = { viewModel.onEvent(AddPostEvent.ContentChanged(it)) },
            label = { Text("وصف المنشور") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        TextField(
            value = state.location,
            onValueChange = { viewModel.onEvent(AddPostEvent.LocationChanged(it)) },
            label = { Text("الموقع") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { viewModel.onEvent(AddPostEvent.Submit) },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (state.isLoading) "جارٍ الإضافة..." else "إضافة المنشور")
        }


        state.error?.let { error ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }


        LaunchedEffect(state.isSuccess) {
            if (state.isSuccess) {
                navController.popBackStack()
            }
        }
    }
}
