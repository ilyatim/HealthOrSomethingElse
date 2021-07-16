package com.example.healtsorsomethingelse.data.database.mainScreen

import android.content.Context
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.network.DatabaseApiServiceHelper
import com.example.healtsorsomethingelse.utils.GoogleNotFountException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlin.random.Random

interface DatabaseRepository {
    suspend fun getContent(): List<UserDatabaseContent>
}

@ViewModelScoped
class DatabaseRepositoryImpl @Inject constructor(
    private val account: GoogleSignInAccount?,
    private val networkServiceHelper: DatabaseApiServiceHelper,
    @ApplicationContext private val context: Context,
) : DatabaseRepository {

    private val numberOfDividerItems: List<Int> = listOf(2, 3, 4)

    private fun List<Int>.getNextRandNumber(): Int {
        return this[Random.nextInt(this.size)]
    }

    override suspend fun getContent(): List<UserDatabaseContent> {
        account?.let {
            val id = it.id ?: "-1"
            val blocks = networkServiceHelper.getMainPageBlockContent(id).toMutableList()
            val sublist = networkServiceHelper.getMainPageSublistContent(id)
            return mergeLists(blocks, sublist)
        } ?: run {
            throw GoogleNotFountException(context.getString(R.string.google_account_not_found))
        }
    }

    private fun mergeLists(
        blocks: MutableList<UserDatabaseContent.Block>,
        subLists: List<UserDatabaseContent.ContentList>
    ): List<UserDatabaseContent> {
        if (blocks.isEmpty()) return subLists

        var nextIntDivider: Int = numberOfDividerItems.getNextRandNumber()
        var iterator: Int = 0
        val mergedList: MutableList<UserDatabaseContent> = mutableListOf()

        mergedList.add(blocks.removeFirst())
        subLists.forEachIndexed { index, item ->
            if (mergedList.last() is UserDatabaseContent.Block) {
                nextIntDivider = numberOfDividerItems.getNextRandNumber()
                iterator = 0
                mergedList.add(item)
            } else {
                mergedList.add(item)
                iterator++
                if (iterator == nextIntDivider) {
                    blocks.removeFirstOrNull()?.let { mergedList.add(it) }
                }
            }
        }

        if (blocks.isNotEmpty()) {
            mergedList.addAll(blocks)
        }

        return mergedList
    }
}