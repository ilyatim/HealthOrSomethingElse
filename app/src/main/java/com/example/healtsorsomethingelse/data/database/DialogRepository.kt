package com.example.healtsorsomethingelse.data.database

/**
 * Interface that provide data to bottom dialog sheet
 * in database fragment
 */
interface DialogRepository {
    suspend fun getDialogInfoById(id: Int): DialogData
}
