package com.example.healtsorsomethingelse.network

import javax.inject.Inject

class ProfileApiServiceHelperImpl @Inject constructor(
    private val apiService: ProfileApiService
) : ProfileApiServiceHelper {

    override suspend fun addNewPurpose(purpose: String, userId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun addCompletedPurpose(purpose: String, userId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserWeightPurpose(userId: Int): String {
        TODO("Not yet implemented")
    }

    override suspend fun getUserHeight(userId: Int): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getUserWeight(userId: Int): Double {
        TODO("Not yet implemented")
    }

    override suspend fun getUserFatPercentage(userId: Int): Double {
        TODO("Not yet implemented")
    }

    override suspend fun getUserPurposes(userId: Int): List<String> {
        TODO("Not yet implemented")
    }

}