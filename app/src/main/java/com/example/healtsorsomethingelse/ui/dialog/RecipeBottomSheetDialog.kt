package com.example.healtsorsomethingelse.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.database.recipes.DialogAction
import com.example.healtsorsomethingelse.data.database.recipes.DialogUiState
import com.example.healtsorsomethingelse.data.database.recipes.Recipe
import com.example.healtsorsomethingelse.databinding.RecipeBottomSheetDialogBinding
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
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

    /*override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
        super.onCreate(savedInstanceState)
    }

    override fun getTheme() = R.style.CustomBottomSheetDialogTheme*/

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
        handleLoading()
        recipeId = arguments?.getInt(ITEM_ID) ?: -1
        handleUiState()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun handleUiState() {
        launch {
            viewModel.state.collect {
                if (_binding == null) return@collect
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
        binding.timeTextView.text = "${TimeUtils.getCookingTime(recipe.cookingTime)}'"
        binding.numberOfPortion.text = "${recipe.portion} порции"//TODO: обработку окончаний

        binding.mainLayout.visible()


        val nHeight = binding.nestedScrollView.height
        val rHeight = binding.root.height
        val pHeihgt = binding.progressBar.height
        val relHeight = binding.relativeLayout.height

        /*ValueAnimator.ofInt(
            rHeight,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            val container = binding.mainLayout
            addUpdateListener { animation ->
                container.updateLayoutParams<ViewGroup.LayoutParams> {
                    height = animation.animatedValue as Int
                }
            }

        }.start()*/
        /*sharedElementEnterTransition = ValueAnimator.ofInt(
            pHeihgt,
            relHeight
        ).apply {
            val container = view?.parent.let { it as View }

            // изменяем высоту контейнера фрагментов
            addUpdateListener { animation ->
                container.updateLayoutParams<ViewGroup.LayoutParams> {
                    height = animation.animatedValue as Int
                }
            }

            // окончании анимации устанавливаем высоту контейнера WRAP_CONTENT
            doOnEnd {
                container.updateLayoutParams<ViewGroup.LayoutParams> {
                    height = ViewGroup.LayoutParams.WRAP_CONTENT
                }
            }
        }.start()*/
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
        private const val ITEM_ID: String = "id"

        fun newInstance(): RecipeBottomSheetDialog {
            return RecipeBottomSheetDialog()
        }

        fun newInstance(id: Int): RecipeBottomSheetDialog {
            return RecipeBottomSheetDialog().apply {
                val args = Bundle()
                args.putInt(ITEM_ID, id)
                this.arguments = args
            }
        }
    }
}