package com.example.healtsorsomethingelse.ui.main.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.profile.ProfileData
import com.example.healtsorsomethingelse.data.profile.UiAction
import com.example.healtsorsomethingelse.data.profile.UiState
import com.example.healtsorsomethingelse.databinding.ProfileFragmentBinding
import com.example.healtsorsomethingelse.extensions.ContextExtensions.showLongToast
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
import com.example.healtsorsomethingelse.extensions.ViewExtensions.hideKeyboard
import com.example.healtsorsomethingelse.extensions.ViewExtensions.visible
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.profile.Adapter
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.profile.AddPurposeListener
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.profile.PlusAdapter
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.profile.PurposesListener
import com.example.healtsorsomethingelse.utils.BaseFragment
import com.example.healtsorsomethingelse.utils.profile.ProfileFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileFragmentViewModel by activityViewModels()
    private lateinit var adapter: Adapter
    private lateinit var plusAdapter: PlusAdapter

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            setDoneButtonState(s.toString())
        }
    }

    private val plusListener = AddPurposeListener {
        binding.addNewPurposeLayout.root.visible()
        binding.addNewPurposeLayout.buttonDone.setOnClickListener { view ->
            //Hide layout
            binding.addNewPurposeLayout.root.gone()
            val inputText: String
            //Clear focus from edit text
            //Hide keyboard
            with(binding.addNewPurposeLayout.editText) {
                inputText = binding.addNewPurposeLayout.editText.text.toString()
                text.clear()
                clearFocus()
                hideKeyboard()
            }
            //Set button state to default
            setDoneButtonState(null)
            //Send intent to save new purpose if it not empty
            if (inputText.isNotEmpty()) {
                viewModel.sendAction(UiAction.AddNewPurpose(inputText))
            }
            //Remove listener's from button and edit text
            view.setOnClickListener(null)
            binding.addNewPurposeLayout.editText.removeTextChangedListener(textWatcher)
        }
        binding.addNewPurposeLayout.editText.addTextChangedListener(textWatcher)

    }

    private fun setDoneButtonState(text: String?) {
        if (!text.isNullOrEmpty()) {
            setButtonRes(R.drawable.ic_baseline_done_24_green, binding.addNewPurposeLayout.buttonDone)
        } else {
            setButtonRes(R.drawable.ic_baseline_done_24, binding.addNewPurposeLayout.buttonDone)
        }
    }

    private fun setButtonRes(@DrawableRes drawableRes: Int, button: ImageButton) {
        button.setImageResource(drawableRes)
    }

    private val purposeListener = object : PurposesListener {
        override fun onCompleteClick(value: String, adapterPosition: Int) {
            viewModel.sendAction(UiAction.CompletePurposes(value, adapterPosition))
        }

        override fun onDismissClick(adapterPosition: Int) {
            viewModel.sendAction(UiAction.DismissPurpose(adapterPosition))
        }
    }

    private fun handleUiState() {
        launch {
            viewModel.state.collect {
                if (_binding == null) return@collect
                when (it) {
                    UiState.Idle -> viewModel.sendAction(UiAction.Loading)
                    UiState.Loading -> handleLoading()
                    is UiState.Content -> fetchContent(it.content)
                    is UiState.Error -> fetchError(it.message)
                }
            }
        }
    }

    private fun fetchError(e: String?) {
        binding.progressBar.gone()
        requireActivity().showLongToast(e)
    }

    private fun setupRecyclerView() {
        plusAdapter = PlusAdapter(layoutInflater, plusListener)
        binding.purposesLayout.recyclerView.adapter = ConcatAdapter(plusAdapter)
    }

    private fun fetchContent(content: ProfileData) {
        binding.progressBar.gone()

        if (this::adapter.isInitialized) {
            adapter.updateList(content.purposes)
        } else {
            adapter = Adapter(layoutInflater, content.purposes.toMutableList(), purposeListener)
            (binding.purposesLayout.recyclerView.adapter as ConcatAdapter).addAdapter(0, adapter)
            binding.purposesLayout.recyclerView.scrollToPosition(0)
        }
        when (content.weightPurpose) {
            0 -> {
                setCheckRadioButtonState(R.id.lose_weight)
            }
            1 -> {
                setCheckRadioButtonState(R.id.gain_weight)
            }
            2 -> {
                setCheckRadioButtonState(R.id.fix_the_weight)
            }
        }

        with (binding.currentParameter) {
            editTextTextPersonHeight.setText(content.height.toString(), TextView.BufferType.EDITABLE)
            editTextTextPersonWeight.setText(content.weight.toString(), TextView.BufferType.EDITABLE)
            editTextTextPersonFat.setText(content.fatPercentage.toString(), TextView.BufferType.EDITABLE)
        }

    }

    private fun setCheckRadioButtonState(@IdRes id: Int ) {
        binding.userTargetLayout.radioGroup.check(id)
    }

    private fun handleLoading() {
        binding.progressBar.visible()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleUiState()
        setupRecyclerView()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}