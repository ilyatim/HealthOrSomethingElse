package com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.core.ui.AbsAdapter
import com.example.core.ui.AbsBindingViewHolder
import com.example.core.utils.DiffUtilImpl
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.database.recipes.RecipeCell
import com.example.healtsorsomethingelse.databinding.ItemRecipeBinding
import com.example.healtsorsomethingelse.extensions.ViewExtensions.click
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*

class RecipesAdapter(
    private val layoutInflater: LayoutInflater,
    private val listener: OnRecipeItemListener,
    list: MutableList<RecipeCell>
) : AbsAdapter<RecipeCell, RecipesViewHolder>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(
            layoutInflater,
            parent,
            listener
        )
    }

    override fun getDiffUtils(
        oldList: List<RecipeCell>,
        newList: List<RecipeCell>,
    ): DiffUtil.Callback {
        return DiffUtilImpl(oldList, newList)
    }
}

class RecipesViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val listener: OnRecipeItemListener,
) : AbsBindingViewHolder<RecipeCell, ItemRecipeBinding>(
    ItemRecipeBinding.inflate(
        layoutInflater,
        parent,
        false
    )
) {
    override fun bind(cell: RecipeCell) = withBinding {
        Glide.with(root)
            .load(cell.imageUrl)
            .placeholder(R.color.colorLightGreen)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            //.centerInside()
            /*.transform(RoundedCorners(20))*/
            .into(imageView)

        likeTextView.text = cell.likes.toString()
        categoryTextView.text = cell.category
        nameTextView.text = cell.name
        timeTextView.text = getActuallyCookingTime(cell.cookingTime)
        numberOfPortion.text = cell.portion.toString()

        root.click()
            .onEach { listener.onRecipeClick(cell.id) }
            .launchIn(MainScope())
        /*binding.root.setOnClickListener {
            this.listener.onRecipeClick(item.id)
        }*/
    }

    private fun getActuallyCookingTime(value: Long): String {
        val sdf = SimpleDateFormat("hh:mm", Locale.getDefault())
        return sdf.format(Date(value))
    }
}

fun interface OnRecipeItemListener {
    fun onRecipeClick(position: Int)
}