package com.vullpes.financeapp.domain.usecases.transaction

import com.vullpes.financeapp.domain.model.Transaction

class ButtonSaveTransactionEnabledUseCase() {

    fun execute(transaction: Transaction): Boolean{

        return transaction.accountFromID > 0 &&
                transaction.value > 0 &&
                ((transaction.transference && transaction.accountTo!! > 0) || transaction.deposit || transaction.withdrawal) &&
                transaction.categoryID > 0
    }
}