package com.example.healtsorsomethingelse.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.healtsorsomethingelse.utils.BaseViewModel
import com.example.healtsorsomethingelse.utils.BindingInflater

abstract class BaseBindingFragment<T : ViewBinding>(
) : Fragment() {

    protected abstract val bindingInflater: BindingInflater<T>
    //protected abstract val viewModel: BaseViewModel

    private var _binding: T? = null
    protected val binding: T
        get() = requireNotNull(_binding) {
            "Nullable view binding"
        }

    protected fun requireBinding(): T = binding

    /*protected abstract fun setupUiHandle()

    override fun onStart() {
        super.onStart()
        setupUiHandle()
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}