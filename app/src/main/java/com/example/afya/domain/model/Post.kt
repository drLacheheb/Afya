package com.example.afya.domain.model

import com.example.afya.data.model.Post as DataPost


data class Post(
    val title: String,
    val drugName: String,
    val content: String,
    val location: String,
    val imageUrl: String? = null,
    val postType: PostType
) {
    fun toDataModel(): DataPost {
        return DataPost(
            id = "",  // أو يمكنك تحديده من مكان آخر إذا كان متاحًا
            title = title,
            drugName = drugName,
            content = content,
            location = location,
            image = imageUrl ?: "",  // استبدال `null` بسلسلة فارغة لأن `image` يبدو إلزاميًا
            postType = postType.toDataModel(),  // تحويل نوع PostType إلى النوع الموجود في `data.model`
            createdAt = System.currentTimeMillis(), // توفير قيمة افتراضية
            updatedAt = System.currentTimeMillis(), // توفير قيمة افتراضية
            expiredAt = System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000) // مثال: انتهاء بعد 7 أيام
        )
    }
}
