package com.example.afya.domain.model

import com.example.afya.data.model.PostType as DataPostType


enum class PostType {
    OFFER, REQUEST
}

fun PostType.toDataModel(): DataPostType {
    return when (this) {
        PostType.OFFER -> DataPostType.OFFER
        PostType.REQUEST -> DataPostType.REQUEST
    }
}









