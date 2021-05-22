package com.example.healtsorsomethingelse.network

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
    suspend fun getUserWeightPurpose(userId: String): String

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