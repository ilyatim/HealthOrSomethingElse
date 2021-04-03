package com.example.healtsorsomethingelse.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

open class ParentViewModel : ViewModel(), CoroutineScope by MainScope()