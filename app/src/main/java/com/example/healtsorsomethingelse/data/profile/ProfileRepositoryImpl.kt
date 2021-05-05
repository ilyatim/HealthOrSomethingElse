package com.example.healtsorsomethingelse.data.profile

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class ProfileRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    val googleSignInAccount: GoogleSignInAccount?
) : ProfileRepository {

}