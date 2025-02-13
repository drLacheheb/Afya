package com.example.afya.repository

import com.example.afya.model.Post
import com.example.afya.model.PostType
import java.util.Date

object PostRepository {
    private val posts = listOf(
        Post(
            id = "1",
            title = "Extra Painkillers",
            content = "I have extra ibuprofen tablets",
            drugName = "Ibuprofen",
            image = "https://images.unsplash.com/photo-1599458252573-56ae36120de1?w=400",
            location = "Algiers",
            postType = PostType.OFFER,
            createdAt = Date(),
            updatedAt = Date(),
            expiredAt = Date(System.currentTimeMillis() + 86400000)
        ),
        Post(
            id = "2",
            title = "Looking for Insulin",
            content = "Urgently need insulin pens",
            drugName = "Insulin",
            image = "https://images.unsplash.com/photo-1615461066841-6116e61058f4?w=400",
            location = "Oran",
            postType = PostType.REQUEST,
            createdAt = Date(),
            updatedAt = Date(),
            expiredAt = Date(System.currentTimeMillis() + 172800000)
        ),
        Post(
            id = "3",
            title = "Extra Vitamins",
            content = "I have extra vitamin C tablets",
            drugName = "Vitamin C",
            image = "https://images.unsplash.com/photo-1620916297394-9b5e3d7e7f9b?w=400",
            location = "Constantine",
            postType = PostType.OFFER,
            createdAt = Date(),
            updatedAt = Date(),
            expiredAt = Date(System.currentTimeMillis() + 86400000)
        ),
        Post(
            id = "4",
            title = "Looking for Antibiotics",
            content = "Urgently need antibiotics",
            drugName = "Amoxicillin",
            image = "https://images.unsplash.com/photo-1599458252573-56ae36120de1?w=400",
            location = "Annaba",
            postType = PostType.REQUEST,
            createdAt = Date(),
            updatedAt = Date(),
            expiredAt = Date(System.currentTimeMillis() + 172800000)
        ),
        Post(
            id = "5",
            title = "Extra Medication",
            content = "I have extra medication for diabetes",
            drugName = "Metformin",
            image = "https://images.unsplash.com/photo-1615461066841-6116e61058f4?w=400",
            location = "Blida",
            postType = PostType.OFFER,
            createdAt = Date(),
            updatedAt = Date(),
            expiredAt = Date(System.currentTimeMillis() + 86400000)
        ),
        Post(
            id = "6",
            title = "Looking for Medication",
            content = "Urgently need medication for high blood pressure",
            drugName = "Amlodipine",
            image = "https://images.unsplash.com/photo-1620916297394-9b5e3d7e7f9b?w=400",
            location = "Tlemcen",
            postType = PostType.REQUEST,
            createdAt = Date(),
            updatedAt = Date(),
            expiredAt = Date(System.currentTimeMillis() + 172800000)
        )
    )

    fun getPosts(): List<Post> {
        return posts.sortedByDescending { it.createdAt }
    }
}
