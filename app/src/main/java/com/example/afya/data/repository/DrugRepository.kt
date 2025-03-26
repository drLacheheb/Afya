package com.example.afya.data.repository

import com.example.afya.data.model.Drug
import kotlinx.coroutines.flow.Flow

interface DrugRepository {

    fun getDrugs(): Flow<List<Drug>>

}
