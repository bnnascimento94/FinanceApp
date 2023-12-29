package com.vullpes.financeapp.domain.usecases.authentication

import com.vullpes.financeapp.data.sharedPreferences.PreferenciasRepository
import javax.inject.Inject

class ClearAllSessionDataUsecase @Inject constructor(private val preferenciasRepository: PreferenciasRepository) {

    fun execute() = preferenciasRepository.cleanData()
}