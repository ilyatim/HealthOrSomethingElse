package com.example.healtsorsomethingelse.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.database.DialogAction
import com.example.healtsorsomethingelse.data.database.DialogData
import com.example.healtsorsomethingelse.data.database.DialogUiState
import com.example.healtsorsomethingelse.databinding.RecipeBottomSheetDialogBinding
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
import com.example.healtsorsomethingelse.extensions.ViewExtensions.visible
import com.example.healtsorsomethingelse.utils.TimeUtils
import com.example.healtsorsomethingelse.utils.database.BottomDialogViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipeBottomSheetDialog : BottomSheetDialogFragment(), CoroutineScope by MainScope() {

    private val viewModel: BottomDialogViewModel by viewModels()
    private var recipeId: Int = -1
    private var _binding: RecipeBottomSheetDialogBinding? = null
    private val binding: RecipeBottomSheetDialogBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecipeBottomSheetDialogBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recipeId = arguments?.getInt("id") ?: -1
        handleUiState()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun handleUiState() {
        launch {
            viewModel.state.collect {
                when (it) {
                    DialogUiState.Idle -> initLoading()
                    DialogUiState.Loading -> handleLoading()
                    is DialogUiState.Content -> fetchContent(it.dialogData)
                    is DialogUiState.Error -> handleError(it.message)
                }
            }
        }
    }

    private fun handleError(message: String) {
        //TODO: handle
    }

    private fun fetchContent(dialogData: DialogData) {
        binding.progressBar.gone()
        binding.mainLayout.visible()

        Glide.with(binding.root)
            .load(dialogData.imageUrl)
            .into(binding.imageView)
        binding.nameTextView.text = dialogData.name
        binding.descriptionTextView.text = dialogData.description
        binding.carbohydratesTextView.text = dialogData.carbohydrates.toString()
        binding.fatsTextView.text = dialogData.fats.toString()
        binding.proteinTextView.text = dialogData.protein.toString()
        binding.gramsTextView.text = dialogData.grams.toString()
        binding.caloriesTextView.text = dialogData.calories.toString()
        binding.likeTextView.text = dialogData.likes.toString()
        binding.timeTextView.text = TimeUtils.getCookingTime(dialogData.cookingTime)
        binding.numberOfPortion.text = dialogData.portion.toString() //TODO: обработку окончаний
    }

    private fun handleLoading() {
        binding.progressBar.visible()
        binding.mainLayout.gone()
    }

    private fun initLoading() {
        viewModel.sendIntent(DialogAction.LoadData(recipeId))
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): RecipeBottomSheetDialog {
            return RecipeBottomSheetDialog()
        }

        fun newInstance(id: Int): RecipeBottomSheetDialog {
            return RecipeBottomSheetDialog().apply {
                val args = Bundle()
                args.putInt("id", id)
                this.arguments = args
            }
        }
    }
}