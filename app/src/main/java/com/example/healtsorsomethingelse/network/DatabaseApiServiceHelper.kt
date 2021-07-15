package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.database.mainScreen.Cell
import javax.inject.Inject

interface DatabaseApiServiceHelper {
    fun getMainPageContent(userId: Int): List<Cell>
}

class DatabaseApiServiceHelperImpl @Inject constructor(
    private val databaseApiService: DatabaseApiService
) : DatabaseApiServiceHelper {
    override fun getMainPageContent(userId: Int): List<Cell> {
        TODO("Not yet implemented")
    }

}