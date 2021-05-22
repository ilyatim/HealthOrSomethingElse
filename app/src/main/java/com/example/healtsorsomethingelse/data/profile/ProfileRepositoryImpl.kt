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
            val userId = googleSignInAccount.id ?: throwNullPointer()

            val name = googleSignInAccount.familyName
            val email = googleSignInAccount.email
            val imageUri = googleSignInAccount.photoUrl
            val weightPurpose: String = "Набрать вес"//networkServiceHelper.getUserWeightPurpose(userId)
            val height = 192//networkServiceHelper.getUserHeight(userId)
            val weight = 192.1//networkServiceHelper.getUserWeight(userId)
            val fatPercentage = 12.3//networkServiceHelper.getUserFatPercentage(userId)
            val purposes: List<String> = listOf()//networkServiceHelper.getUserPurposes(userId)

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
        networkServiceHelper.addCompletedPurpose(purpose, googleSignInAccount?.id ?: "-1")
    }

    override suspend fun addNewPurpose(purpose: String) {
        networkServiceHelper.addNewPurpose(purpose, googleSignInAccount?.id ?: "-1")
    }

}