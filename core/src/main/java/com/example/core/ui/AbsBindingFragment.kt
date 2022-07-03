package com.example.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.core.utils.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Abs fragment with view model
 */
abstract class AbsFragment<V: ViewBinding, T, K, E, VM: BaseViewModel<T, K, E>>(

) : AbsBindingFragment<V>(), Tag {
    /**
     * Base view model
     */
    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            viewModel.getEventFlow().collect(::collectUiEvent)
        }
        lifecycleScope.launch {
            viewModel.getUiState().collect(::collectUiState)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * Collect ui state from view model
     * @param state ui state
     */
    abstract fun collectUiState(state: T)

    /**
     * Collect ui event from view model
     * @param event ui event
     */
    abstract fun collectUiEvent(event: E)
}

/**
 * Abstract fragment class with [ViewBinding]
 */
abstract class AbsBindingFragment<V: ViewBinding>(

) : Fragment(), Tag {
    abstract val viewInflater: ViewInflater<V>

    private var _binding: V? = null
    private val binding: V
        get() = requireNotNull(_binding) {
            "require binding not null"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = viewInflater.invoke(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

interface Tag {
    val TAG: String
}