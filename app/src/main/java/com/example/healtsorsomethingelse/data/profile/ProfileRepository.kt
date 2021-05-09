package com.example.healtsorsomethingelse.data.profile

/**
 * Interface for ProfileFragment that provide data
 */
interface ProfileRepository {

    /**
     * Return class that contain user profile data, e.g. Name, Surname.
     * @return [ProfileData]
     */
    suspend fun getProfileData(): ProfileData

    /**
     * Save user completed purposes
     * @param purpose completed purpose [String]
     */
    suspend fun savePurposes(purpose: String)

    /**
     * Add new user purpose
     * @param purpose new purpose [String]
     */
    suspend fun addNewPurpose(purpose: String)
}