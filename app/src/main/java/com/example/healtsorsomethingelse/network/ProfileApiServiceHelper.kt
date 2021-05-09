package com.example.healtsorsomethingelse.network

interface ProfileApiServiceHelper {
    suspend fun addNewPurpose(purpose: String)
    suspend fun addCompletedPurpose(purpose: String)
}