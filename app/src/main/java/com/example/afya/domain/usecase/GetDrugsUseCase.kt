package com.example.afya.domain.usecase

import com.example.afya.data.repository.DrugRepository
import javax.inject.Inject

class GetDrugsUseCase @Inject constructor(private val drugRepository: DrugRepository) {

    operator fun invoke() = drugRepository.getDrugs()
}