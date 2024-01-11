package com.vullpes.authentication.usecases

import com.vullpes.sharedpreferences.PreferenciasRepository
import javax.inject.Inject

class AllowBiometricsUsecase @Inject constructor(private val preferenciasRepository: PreferenciasRepository) {

    fun execute(allowBiometrics:Boolean) = preferenciasRepository.allowUseBiometrics(allowBiometrics)
}