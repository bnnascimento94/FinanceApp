package com.vullpes.authentication.usecases

import com.vullpes.sharedpreferences.PreferenciasRepository
import javax.inject.Inject

class GetBiometricStatusUsecase @Inject constructor(private val preferenciasRepository: com.vullpes.sharedpreferences.PreferenciasRepository) {

    fun execute():Boolean = preferenciasRepository.getAllowBiometrics()
}