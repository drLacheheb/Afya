package com.example.afya.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.afya.data.model.PostType
import com.example.afya.presentation.AddPostViewModel
import com.example.afya.presentation.AddPostEvent
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.ui.graphics.RectangleShape
import kotlinx.coroutines.delay
import java.text.ParseException


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostScreen(
    //navController: NavController,

) {
    val viewModel: AddPostViewModel = hiltViewModel()
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var drugName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var postType by remember { mutableStateOf<PostType?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var showSuccessMessage by remember { mutableStateOf(false) }

    // استخدام ActivityResultContracts لفتح مستعرض الملفات
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri = uri
            viewModel.onEvent(AddPostEvent.ImageUrlChanged(uri?.toString() ?: ""))
        }
    )


    // LaunchedEffect to show success message for 5 seconds
    //LaunchedEffect(showSuccessMessage) {
    //    if (showSuccessMessage) {
    //        delay(3000) // Show the message for 3 seconds
    //        showSuccessMessage = false // Hide the message after 3 seconds
    //        navController.popBackStack()
    //   }
    //}

   Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        // حقل تحميل الصورة - مستطيل
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp) // تحديد الارتفاع للمربع المستطيل
        ) {
            // إذا تم تحميل صورة، عرضها داخل المستطيل
            imageUri?.let {
                Image(
                    painter = rememberImagePainter(it),
                    contentDescription = "Uploaded Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RectangleShape) // جعل الصورة مستطيلة
                )
            }

            // زر رفع الصورة يظهر فقط إذا كانت الصورة غير محمّلة
            if (imageUri == null) {
                Button(
                    onClick = { pickImageLauncher.launch("image/*") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp) // جعل الزر مستطيلًا أكبر
                        .padding(16.dp)
                ) {
                    Text("تحميل صورة", color = Color.White) // نص الزر
                }
            }
        }

        // باقي الحقول الأخرى (عنوان المنشور، المحتوى، الاسم الدوائي، إلخ...)
        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
                viewModel.onEvent(AddPostEvent.TitleChanged(it))
                showError = false
            },
            label = { Text("عنوان المنشور") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && title.isEmpty()
        )

        OutlinedTextField(
            value = content,
            onValueChange = {
                content = it
                viewModel.onEvent(AddPostEvent.ContentChanged(it))
                showError = false
            },
            label = { Text("محتوى المنشور") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && content.isEmpty()
        )

        OutlinedTextField(
            value = drugName,
            onValueChange = {
                drugName = it
                viewModel.onEvent(AddPostEvent.DrugNameChanged(it))
                showError = false
            },
            label = { Text("اسم الدواء") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && drugName.isEmpty()
        )

        OutlinedTextField(
            value = location,
            onValueChange = {
                location = it
                viewModel.onEvent(AddPostEvent.LocationChanged(it))
                showError = false
            },
            label = { Text("الموقع") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && location.isEmpty()
        )

        // حقل اختيار نوع المنشور
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = postType?.type ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("نوع المنشور") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                trailingIcon = {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        Modifier.clickable { expanded = true }
                    )
                }
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                PostType.values().forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type.type) },
                        onClick = {
                            postType = type
                            expanded = false
                        }
                    )
                }
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(
                onClick = {
                    showError = false

                    if (title.isEmpty() || content.isEmpty() || drugName.isEmpty() || location.isEmpty()) {
                        showError = true
                    } else {
                        viewModel.onEvent(AddPostEvent.Submit)
                        showSuccessMessage = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("اضافة منشور")
            }


            // Display success message for 3 seconds with checkmark
            if (showSuccessMessage) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle, // علامة صح
                            contentDescription = "Success",
                            tint = Color.Green
                        )
                        Text(
                            text = "تمت اضافة المنشور بنجاح!",
                            style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
                            color = Color.Green
                        )
                    }
                }
            }


            // Display error message if any field is missing
            if (showError) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "من فضلك املئ كل الحقول.",
                        color = Color.Red,
                        fontSize = 14.sp,
                        style = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                        modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}