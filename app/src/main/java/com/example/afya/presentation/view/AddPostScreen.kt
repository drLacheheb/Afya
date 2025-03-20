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
        // حقل العنوان
        TextField(
            value = state.title,
            onValueChange = { viewModel.onEvent(AddPostEvent.TitleChanged(it)) },
            label = { Text("عنوان المنشور") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // حقل اسم الدواء
        TextField(
            value = state.drugName,
            onValueChange = { viewModel.onEvent(AddPostEvent.DrugNameChanged(it)) },
            label = { Text("اسم الدواء") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // حقل المحتوى
        TextField(
            value = state.content,
            onValueChange = { viewModel.onEvent(AddPostEvent.ContentChanged(it)) },
            label = { Text("وصف المنشور") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // حقل الموقع
        TextField(
            value = state.location,
            onValueChange = { viewModel.onEvent(AddPostEvent.LocationChanged(it)) },
            label = { Text("الموقع") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // زر إضافة المنشور
        Button(
            onClick = { viewModel.onEvent(AddPostEvent.Submit) },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (state.isLoading) "جارٍ الإضافة..." else "إضافة المنشور")
        }

        // عرض رسالة خطأ إذا وجدت
        state.error?.let { error ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }

        // العودة للشاشة السابقة بعد نجاح الإضافة
        LaunchedEffect(state.isSuccess) {
            if (state.isSuccess) {
                navController.popBackStack()
            }
        }
    }
}
