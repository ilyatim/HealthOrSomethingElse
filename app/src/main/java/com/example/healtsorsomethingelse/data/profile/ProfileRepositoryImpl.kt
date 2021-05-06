package com.example.healtsorsomethingelse.data.profile

import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class ProfileRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    val googleSignInAccount: GoogleSignInAccount?
) : ProfileRepository {

    override fun getProfileData(): ProfileData {
        return ProfileData("plug", listOf("Сделать столько-то приседаний", "сделать столько-то подтягиваний"))
    }

    override fun savePurposes(purpose: String) {
        Log.d("Sometag", "save completed purposes")
    }

    override fun addNewPurpose(purpose: String) {
        Log.d("Sometag", "add new purpose")
    }

}