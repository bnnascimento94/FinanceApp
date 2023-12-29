package com.vullpes.financeapp.domain.usecases.authentication

import com.vullpes.financeapp.data.sharedPreferences.PreferenciasRepository
import javax.inject.Inject

class AllowBiometricsUsecase @Inject constructor(private val preferenciasRepository: PreferenciasRepository) {

    fun execute(allowBiometrics:Boolean) = preferenciasRepository.allowUseBiometrics(allowBiometrics)
}