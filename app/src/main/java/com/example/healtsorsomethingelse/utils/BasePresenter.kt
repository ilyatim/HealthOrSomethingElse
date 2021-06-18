package com.example.healtsorsomethingelse.utils

abstract class BasePresenter<T: BaseMvpView> : BaseMvpPresenter<T> {

    var viewMvp: T? = null
        private set

    protected open fun onViewDetached() {}

    override fun attachView(view: T) {
        this.viewMvp = view
    }

    override fun detachView() {
        this.viewMvp = null
        onViewDetached()
    }

    protected fun isMvpViewAttached(): Boolean = viewMvp != null
}