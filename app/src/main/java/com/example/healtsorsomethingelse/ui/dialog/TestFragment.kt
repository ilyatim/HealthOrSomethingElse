package com.example.healtsorsomethingelse.ui.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.healtsorsomethingelse.databinding.HomeFragmentBinding
import com.example.healtsorsomethingelse.ui.BaseBindingFragment
import com.example.healtsorsomethingelse.utils.BindingInflater

class TestFragment : BaseBindingFragment<HomeFragmentBinding>() {
    override val bindingInflater: BindingInflater<HomeFragmentBinding> =
        BindingInflater { layoutInflater, container, attachToParent ->
            HomeFragmentBinding.inflate(
                layoutInflater,
                container,
                attachToParent
            )
        }
}