package com.example.healtsorsomethingelse.data.database

import javax.inject.Inject

class DialogRepositoryImpl @Inject constructor() : DialogRepository {
    override suspend fun getDialogInfoById(id: Int): DialogData {
        TODO("Not yet implemented")
    }
}
