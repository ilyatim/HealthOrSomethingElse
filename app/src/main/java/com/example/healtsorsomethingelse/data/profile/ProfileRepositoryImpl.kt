package com.example.healtsorsomethingelse.data.profile

import android.content.Context
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
        googleSignInAccount?.let { googleSignInAccount ->
            val userId = googleSignInAccount.id?.toInt() ?: throwNullPointer()

            val name = googleSignInAccount.familyName
            val email = googleSignInAccount.email
            val imageUri = googleSignInAccount.photoUrl
            val weightPurpose: String = networkServiceHelper.getUserWeightPurpose(userId)
            val height = networkServiceHelper.getUserHeight(userId)
            val weight = networkServiceHelper.getUserWeight(userId)
            val fatPercentage = networkServiceHelper.getUserFatPercentage(userId)
            val purposes: List<String> = networkServiceHelper.getUserPurposes(userId)

            return ProfileData(
                name,
                email,
                imageUri?.toString(),
                weightPurpose,
                height,
                weight,
                fatPercentage,
                purposes
            )
        } ?: kotlin.run {
            throwNullPointer()
        }
    }

    private fun throwNullPointer(): Nothing {
        throw NullPointerException("Google account must be not null")
    }

    override suspend fun savePurposes(purpose: String) {
        networkServiceHelper.addCompletedPurpose(purpose, googleSignInAccount?.id?.toInt() ?: return)
    }

    override suspend fun addNewPurpose(purpose: String) {
        networkServiceHelper.addNewPurpose(purpose, googleSignInAccount?.id?.toInt() ?: return)
    }

}