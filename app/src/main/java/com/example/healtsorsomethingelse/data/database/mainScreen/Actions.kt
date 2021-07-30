package com.example.healtsorsomethingelse.data.database.mainScreen

sealed class Actions {
    object LoadContent : Actions()
    data class SetBarHeight(val height: Int) : Actions()
}
