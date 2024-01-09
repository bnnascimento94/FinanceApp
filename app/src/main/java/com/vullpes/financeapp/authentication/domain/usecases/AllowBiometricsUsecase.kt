package com.vullpes.financeapp.authentication.domain.usecases

import com.vullpes.sharedpreferences.PreferenciasRepository
import javax.inject.Inject

class AllowBiometricsUsecase @Inject constructor(private val preferenciasRepository: com.vullpes.sharedpreferences.PreferenciasRepository) {

    fun execute(allowBiometrics:Boolean) = preferenciasRepository.allowUseBiometrics(allowBiometrics)
}