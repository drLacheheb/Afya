package com.example.afya.presentation

import android.net.Uri
import com.example.afya.data.model.PostType
import java.util.Date

sealed class AddPostEvent {
    data class TitleChanged(val title: String) : AddPostEvent()
    data class DrugNameChanged(val drugName: String) : AddPostEvent()
    data class ContentChanged(val content: String) : AddPostEvent()
    data class LocationChanged(val location: String) : AddPostEvent()
    data class ImageUrlChanged(val url: String) : AddPostEvent()
    data class PostTypeSelected(val postType: PostType) : AddPostEvent()
    object Submit : AddPostEvent()
}
