package com.example.healtsorsomethingelse.network

import com.example.healtsorsomethingelse.data.database.mainScreen.UserDatabaseContent
import javax.inject.Inject

interface DatabaseApiServiceHelper {
    /**
     * Return list of blocks for main page
     */
    suspend fun getMainPageBlockContent(userId: String): List<UserDatabaseContent.Block>

    /**
     * Return list of sublist for main page
     */
    suspend fun getMainPageSublistContent(userId: String): List<UserDatabaseContent.ContentList>
}

class DatabaseApiServiceHelperImpl @Inject constructor(
    private val databaseApiService: DatabaseApiService,
) : DatabaseApiServiceHelper {
    override suspend fun getMainPageBlockContent(userId: String): List<UserDatabaseContent.Block> {
        return databaseApiService.getDatabaseMainPageBlockList(userId = userId)
    }

    override suspend fun getMainPageSublistContent(userId: String): List<UserDatabaseContent.ContentList> {
        return databaseApiService.getDatabaseMainPageSublist(userId = userId)
    }
}