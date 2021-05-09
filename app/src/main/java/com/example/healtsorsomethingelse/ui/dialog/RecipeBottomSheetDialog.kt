package com.example.healtsorsomethingelse.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.database.DialogAction
import com.example.healtsorsomethingelse.data.database.DialogUiState
import com.example.healtsorsomethingelse.data.database.Recipe
import com.example.healtsorsomethingelse.databinding.RecipeBottomSheetDialogBinding
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
import com.example.healtsorsomethingelse.extensions.ViewExtensions.invisible
import com.example.healtsorsomethingelse.extensions.ViewExtensions.visible
import com.example.healtsorsomethingelse.utils.TimeUtils
import com.example.healtsorsomethingelse.utils.database.BottomDialogViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
                    is DialogUiState.Content -> fetchContent(it.recipe)
                    is DialogUiState.Error -> handleError(it.message)
                }
            }
        }
    }

    private fun handleError(message: String?) {
        dismiss()
    }

    private fun fetchContent(recipe: Recipe) {
        binding.progressBar.gone()
        binding.mainLayout.visible()

        Glide.with(binding.root)
            .load(recipe.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .centerCrop()
            .placeholder(R.color.colorLightGreen)
            .into(binding.imageView)
        binding.nameTextView.text = recipe.name
        binding.descriptionTextView.text = recipe.description
        binding.carbohydratesTextView.text = recipe.carbohydrates.toString()
        binding.fatsTextView.text = recipe.fats.toString()
        binding.proteinTextView.text = recipe.protein.toString()
        binding.gramsTextView.text = recipe.grams.toString()
        binding.caloriesTextView.text = recipe.calories.toString()
        binding.likeTextView.text = recipe.likes.toString()
        binding.timeTextView.text = TimeUtils.getCookingTime(recipe.cookingTime)
        binding.numberOfPortion.text = recipe.portion.toString() //TODO: обработку окончаний
    }

    private fun handleLoading() {
        binding.progressBar.visible()
        binding.mainLayout.invisible()
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