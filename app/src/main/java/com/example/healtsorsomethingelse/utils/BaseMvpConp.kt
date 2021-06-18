package com.example.healtsorsomethingelse.utils

interface BaseMvpView {

}

interface BaseMvpPresenter<V: BaseMvpView> {
    fun attachView(view: V)
    fun detachView()
}