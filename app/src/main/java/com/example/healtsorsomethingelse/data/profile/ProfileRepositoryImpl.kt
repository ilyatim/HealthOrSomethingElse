package com.example.healtsorsomethingelse.data.profile

import android.content.Context
import android.util.Log
import com.example.healtsorsomethingelse.network.ProfileApiServiceHelper
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class ProfileRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val googleSignInAccount: GoogleSignInAccount?,
    private val networkServiceHelper: ProfileApiServiceHelper
) : ProfileRepository {

    override suspend fun getProfileData(): ProfileData {
        return ProfileData("plug", listOf("Сделать столько-то приседаний", "сделать столько-то подтягиваний"))
    }

    override suspend fun savePurposes(purpose: String) {
        networkServiceHelper.addCompletedPurpose(purpose)
    }

    override suspend fun addNewPurpose(purpose: String) {
        networkServiceHelper.addNewPurpose(purpose)
    }

}