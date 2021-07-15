package com.example.healtsorsomethingelse.network

import javax.inject.Inject

/**
 * Interface that provide data from server
 */
interface ProfileApiServiceHelper {
    /**
     * Add new purpose on server
     * @param purpose purpose
     * @param userId user ID
     * @return token: [String]
     */
    suspend fun addNewPurpose(purpose: String, userId: String): String

    /**
     * Add completed purpose on server
     * @param purpose completed purpose
     * @param userId user ID
     * @return token [String]
     */
    suspend fun addCompletedPurpose(purpose: String, userId: String): String

    /**
     * Return user weight purpose
     * @param userId user ID
     * @return token [String]
     */
    suspend fun getUserWeightPurpose(userId: String): Int

    /**
     * Return user height
     * @param userId user ID
     * @return User height [Int]
     */
    suspend fun getUserHeight(userId: String): Int

    /**
     * Return user weight
     * @param userId user ID
     * @return User Weight [Double]
     */
    suspend fun getUserWeight(userId: String): Double

    /**
     * Return fat percentage
     * @param userId user ID
     * @return User Fat Percentage [Double]
     */
    suspend fun getUserFatPercentage(userId: String): Double

    /**
     * Return user purpuses
     * @param userId user ID
     * @return list of user purposes [List]
     */
    suspend fun getUserPurposes(userId: String): List<String>
}

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