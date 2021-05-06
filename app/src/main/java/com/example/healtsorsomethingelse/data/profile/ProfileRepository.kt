package com.example.healtsorsomethingelse.data.profile

/**
 * Interface for ProfileFragment that provide data
 */
interface ProfileRepository {

    /**
     * Return class that contain user profile data, e.g. Name, Surname.
     * @return [ProfileData]
     */
    fun getProfileData(): ProfileData

    /**
     * Save user completed purposes
     * @param purpose completed purpose [String]
     */
    fun savePurposes(purpose: String)

    /**
     * Add new user purpose
     * @param purpose new purpose [String]
     */
    fun addNewPurpose(purpose: String)
}