package com.example.afya.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afya.data.model.Drug
import com.example.afya.data.repository.DrugRepositoryImpl
import com.example.afya.domain.usecase.GetDrugsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DrugState(
    val drugs: List<Drug> = emptyList(),
)

@HiltViewModel
class DrugViewModel @Inject constructor(private val getDrugsUseCase: GetDrugsUseCase) : ViewModel() {
    private val _drugState = MutableStateFlow(DrugState())
    val drugState: StateFlow<DrugState> = _drugState.asStateFlow()

    init {
        loadDrugs()
    }

    fun loadDrugs() {
        viewModelScope.launch {

            getDrugsUseCase().collect{ drugs ->
                _drugState.value = _drugState.value.copy(
                    drugs = drugs
                )
            }

        }
    }
}
