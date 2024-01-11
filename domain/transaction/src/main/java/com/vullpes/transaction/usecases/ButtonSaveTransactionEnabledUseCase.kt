package com.vullpes.transaction.usecases

import com.vullpes.transaction.Transaction

class ButtonSaveTransactionEnabledUseCase() {

    fun execute(transaction: Transaction): Boolean{


        return (transaction.accountFromID > 0) &&
                (transaction.value > 0) &&
                ((transaction.transference && ((transaction.accountTo ?: 0) > 0)) || transaction.deposit || transaction.withdrawal) &&
                ((!transaction.transference && ((transaction.categoryID ?: 0) > 0)) || (transaction.transference && transaction.categoryID == 0))
    }
}