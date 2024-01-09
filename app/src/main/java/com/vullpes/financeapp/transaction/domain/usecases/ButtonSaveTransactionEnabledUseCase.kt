package com.vullpes.financeapp.transaction.domain.usecases

import com.vullpes.financeapp.transaction.domain.Transaction

class ButtonSaveTransactionEnabledUseCase() {

    fun execute(transaction: Transaction): Boolean{


        return (transaction.accountFromID > 0) &&
                (transaction.value > 0) &&
                ((transaction.transference && ((transaction.accountTo ?: 0) > 0)) || transaction.deposit || transaction.withdrawal) &&
                ((!transaction.transference && ((transaction.categoryID ?: 0) > 0)) || (transaction.transference && transaction.categoryID == 0))
    }
}