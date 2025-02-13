package com.example.afya.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afya.model.Drug
import com.example.afya.repository.DrugRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DrugState(
    val drugs: List<Drug> = emptyList(),
)

class DrugViewModel : ViewModel() {
    private val _drugState = MutableStateFlow(DrugState())
    val drugState: StateFlow<DrugState> = _drugState.asStateFlow()

    init {
        loadDrugs()
    }

    fun loadDrugs() {
        viewModelScope.launch {

                delay(2000)
                val drugs = DrugRepository.getDrugs()
                _drugState.value = _drugState.value.copy(
                    drugs = drugs
                )

        }
    }
}
