package com.example.healtsorsomethingelse.utils

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import javax.inject.Inject

/*@HiltViewModel*/
open class BaseViewModel /*@Inject constructor()*/ : ViewModel(), CoroutineScope by MainScope()