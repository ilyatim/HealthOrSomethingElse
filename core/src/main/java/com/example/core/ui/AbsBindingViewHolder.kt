package com.example.core.ui

import androidx.viewbinding.ViewBinding

abstract class AbsBindingViewHolder<T, B : ViewBinding>(
    protected val binding: B
) : AbsViewHolder<T>(binding.root), BindingView<B> {
    override fun viewBinding(block: B.() -> Unit) {
        block(binding)
    }
}

interface BindingView<T: ViewBinding> {
    fun viewBinding(block: T.() -> Unit)
}

