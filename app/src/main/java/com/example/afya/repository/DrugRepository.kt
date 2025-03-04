package com.example.afya.repository

import com.example.afya.model.Drug

object DrugRepository {
    private val drugs = listOf(
        Drug(
            id = "1",
            name = "Ibuprofen",
            details = "Nonsteroidal anti-inflammatory drug (NSAID) used to reduce fever and treat pain or inflammation" ,
            image = "https://www.pharmaciedesteinfort.com/media/catalog/product/cache/e34e4c303aca0a6b6a6aff8f2907f7d5/i/b/ibuprofen-eg-400-mg-30-cpr-0001.jpg\n",
        ),
        Drug(
            id = "2",
            name = "Insulin",
            details = "Hormone used to treat diabetes by regulating blood sugar levels",
            image = "https://images.unsplash.com/photo-1615461066841-6116e61058f4?w=400",
        ),
        Drug(
            id = "3",
            name = "Vitamin C",
            details = "Essential nutrient that supports the immune system and acts as an antioxidant",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiqPbXCwK9Q1pHzd0pqnv3V-Oyge5dC1ZI6mAZW6PL6gG0b95OsKMLSvFv_rvU-hciuzg&usqp=CAU\n",
        ),
        Drug(
            id = "4",
            name = "Amoxicillin",
            details = "Antibiotic used to treat various bacterial infections",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTYGQH_2GBtX4nZg-HZw1Upg5VDSra-9YXqA&s\n",
        ),
        Drug(
            id = "5",
            name = "Metformin",
            details = "First-line medication for the treatment of type 2 diabetes",
            image = "https://www.poison.org/-/media/images/shared/articles/metformin.jpg\n",
        ),
        Drug(
            id = "6",
            name = "Amlodipine",
            details = "Calcium channel blocker used to treat high blood pressure and angina",
            image = "https://www.myhealthshop.ch/media/ee/14/f4/1651329345/7785693_PICBACK3D_F.jpg\n",
        )
    )

    fun getDrugs(): List<Drug> {
        return drugs
    }
}