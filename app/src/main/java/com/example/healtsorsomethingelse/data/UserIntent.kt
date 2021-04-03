package com.example.healtsorsomethingelse.data

sealed class UserIntent {
    object GetData : UserIntent()
    object SendData : UserIntent()
}
