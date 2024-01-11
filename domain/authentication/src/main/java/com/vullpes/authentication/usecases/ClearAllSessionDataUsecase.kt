package com.vullpes.authentication.usecases

import com.vullpes.sharedpreferences.PreferenciasRepository
import javax.inject.Inject

class ClearAllSessionDataUsecase @Inject constructor(private val preferenciasRepository: com.vullpes.sharedpreferences.PreferenciasRepository) {

    fun execute() = preferenciasRepository.cleanData()
}