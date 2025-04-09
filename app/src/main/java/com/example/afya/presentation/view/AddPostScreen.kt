@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.afya.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.afya.data.model.PostType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostScreen() {
    var expanded by remember { mutableStateOf(false) }
    var postType by remember { mutableStateOf(PostType.NORMAL) }
    var title by remember { mutableStateOf("") }
    var drugName by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("إضافة منشور", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // حقل إدخال عنوان المنشور
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("عنوان المنشور") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // حقل إدخال اسم الدواء
        OutlinedTextField(
            value = drugName,
            onValueChange = { drugName = it },
            label = { Text("اسم الدواء") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // حقل إدخال محتوى المنشور
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("محتوى المنشور") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(8.dp))

        // حقل إدخال الموقع
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("الموقع") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // حقل إدخال رابط الصورة
        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("رابط الصورة (اختياري)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // قائمة منسدلة لاختيار نوع المنشور
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ExposedDropdownMenuItem(onClick = {
                postType = PostType.NORMAL
                expanded = false
            }) {
                Text("منشور عادي")
            }
            ExposedDropdownMenuItem(onClick = {
                postType = PostType.FEATURED
                expanded = false
            }) {
                Text("منشور مميز")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // زر إضافة المنشور
        Button(
            onClick = {
                // هنا يمكنك إضافة الكود لإرسال البيانات إلى ViewModel أو قاعدة البيانات
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("إضافة المنشور")
        }
    }
}

@Composable
fun ExposedDropdownMenuItem(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {

}

@Composable
fun ExposedDropdownMenu(expanded: Boolean, onDismissRequest: () -> Unit, content: @Composable () -> Unit) {
    DropdownMenu(expanded = expanded, onDismissRequest = onDismissRequest) {
        content()
    }
}

