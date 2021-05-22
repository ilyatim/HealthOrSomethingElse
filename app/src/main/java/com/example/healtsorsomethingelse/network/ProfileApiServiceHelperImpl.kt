package com.example.healtsorsomethingelse.network

import javax.inject.Inject

class ProfileApiServiceHelperImpl @Inject constructor(
    private val apiService: ProfileApiService
) : ProfileApiServiceHelper {

    override suspend fun addNewPurpose(purpose: String, userId: String): String {
        return apiService.addNewPurpose(purpose, userId)
    }

    override suspend fun addCompletedPurpose(purpose: String, userId: String): String {
        return apiService.addCompletedPurpose(purpose, userId)
    }

    override suspend fun getUserWeightPurpose(userId: String): Int {
        return apiService.getUserWeightPurpose(userId)
    }

    override suspend fun getUserHeight(userId: String): Int {
        return apiService.getUserHeight(userId)
    }

    override suspend fun getUserWeight(userId: String): Double {
        return apiService.getUserWeight(userId)
    }

    override suspend fun getUserFatPercentage(userId: String): Double {
        return apiService.getUserFatPercentage(userId)
    }

    override suspend fun getUserPurposes(userId: String): List<String> {
        return apiService.getUserPurposes(userId)
    }

}