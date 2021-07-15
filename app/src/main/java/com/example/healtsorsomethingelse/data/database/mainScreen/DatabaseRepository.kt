package com.example.healtsorsomethingelse.data.database.mainScreen

import android.content.Context
import android.content.res.Resources
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.di.GoogleSingInModule
import com.example.healtsorsomethingelse.network.DatabaseApiServiceHelper
import com.example.healtsorsomethingelse.utils.GoogleNotFountException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface DatabaseRepository {
    suspend fun getContent(): List<Cell>
}

@ViewModelScoped
class DatabaseRepositoryImpl @Inject constructor(
    private val account: GoogleSignInAccount?,
    private val networkServiceHelper: DatabaseApiServiceHelper,
    @ApplicationContext private val context: Context,
) : DatabaseRepository {
    override suspend fun getContent(): List<Cell> {
        return suspendCoroutine { coroutine ->
            account?.let {
                coroutine.resume(listOf())
            } ?: kotlin.run {
                coroutine.resumeWithException(GoogleNotFountException(context.getString(R.string.google_account_not_found)))
            }
        }
    }
}