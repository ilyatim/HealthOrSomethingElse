package com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.database.RecipeCell
import com.example.healtsorsomethingelse.databinding.ItemRecipeBinding
import com.example.healtsorsomethingelse.utils.DiffUtilImpl
import java.text.SimpleDateFormat
import java.util.*

class RecipesAdapter(
    private val layoutInflater: LayoutInflater,
    private val listener: OnRecipeItemListener,
    private val items: MutableList<RecipeCell>
) : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    private lateinit var diffUtil: DiffUtilImpl<RecipeCell>

    fun updateList(newList: List<RecipeCell>) {
        diffUtil = DiffUtilImpl(items, newList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        diffUtilResult.dispatchUpdatesTo(this)

        items.clear()
        items.addAll(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(
            layoutInflater,
            parent,
            listener
        )
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class RecipesViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        private val listener: OnRecipeItemListener,
        private val binding: ItemRecipeBinding =
            ItemRecipeBinding.inflate(
                layoutInflater,
                parent,
                false
            )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecipeCell) {
            Glide.with(binding.root)
                .load(item.imageUrl)
                .placeholder(R.color.colorLightGreen)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                //.centerInside()
                /*.transform(RoundedCorners(20))*/
                .into(binding.imageView)
            binding.likeTextView.text = item.likes.toString()
            binding.categoryTextView.text = item.category
            binding.nameTextView.text = item.name
            binding.timeTextView.text = getActuallyCookingTime(item.cookingTime)
            binding.numberOfPortion.text = item.portion.toString()

            binding.root.setOnClickListener {
                this.listener.onRecipeClick(item.id)
            }
        }

        private fun getActuallyCookingTime(value: Long): String {
            val sdf = SimpleDateFormat("hh:mm", Locale.getDefault())
            return sdf.format(Date(value))
        }
    }
}

fun interface OnRecipeItemListener {
    fun onRecipeClick(position: Int)
}