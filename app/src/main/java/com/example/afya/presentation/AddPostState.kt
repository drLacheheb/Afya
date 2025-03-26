package com.example.afya.presentation

import android.net.Uri
import com.example.afya.data.model.PostType
import java.util.Date

data class AddPostState(
    val title: String = "",
    val drugName: String = "",
    val content: String = "",
    val location: String = "",
    val imageUrl: String = "",
    val postType: PostType = PostType.OFFER, // القيمة الافتراضية
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)