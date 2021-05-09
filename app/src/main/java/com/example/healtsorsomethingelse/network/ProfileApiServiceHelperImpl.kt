package com.example.healtsorsomethingelse.network

import javax.inject.Inject

class ProfileApiServiceHelperImpl @Inject constructor(
    private val apiService: ProfileApiService
) : ProfileApiServiceHelper {

    override suspend fun addNewPurpose(purpose: String) {
        //apiService.
    }

    override suspend fun addCompletedPurpose(purpose: String) {

    }

}