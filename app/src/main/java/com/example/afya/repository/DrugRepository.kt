package com.example.afya.repository

import com.example.afya.model.Drug

object DrugRepository {
    private val drugs = listOf(
        Drug(
            id = "1",
            name = "Ibuprofen",
            details = "Nonsteroidal anti-inflammatory drug (NSAID) used to reduce fever and treat pain or inflammation"
        ),
        Drug(
            id = "2",
            name = "Insulin",
            details = "Hormone used to treat diabetes by regulating blood sugar levels"
        ),
        Drug(
            id = "3",
            name = "Vitamin C",
            details = "Essential nutrient that supports the immune system and acts as an antioxidant"
        ),
        Drug(
            id = "4",
            name = "Amoxicillin",
            details = "Antibiotic used to treat various bacterial infections"
        ),
        Drug(
            id = "5",
            name = "Metformin",
            details = "First-line medication for the treatment of type 2 diabetes"
        ),
        Drug(
            id = "6",
            name = "Amlodipine",
            details = "Calcium channel blocker used to treat high blood pressure and angina"
        )
    )

    fun getDrugs(): List<Drug> {
        return drugs
    }
}
