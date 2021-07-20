package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.database

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginStart
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.data.database.mainScreen.UserDatabaseContent
import com.example.healtsorsomethingelse.databinding.ItemDatabaseSublistBinding
import com.example.healtsorsomethingelse.di.ConstManagerInterface
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database.HorizontalSublistAdapter
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseListener
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseViewHolder
import com.example.healtsorsomethingelse.utils.AbsViewHolder
import com.example.healtsorsomethingelse.utils.GravitySnapHelper
import com.example.healtsorsomethingelse.utils.database.StateHandler
import dagger.hilt.EntryPoints

class SubListViewHolder(
    private val layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val listener: DatabaseListener,
    private val binding: ItemDatabaseSublistBinding =
        ItemDatabaseSublistBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder<UserDatabaseContent>(binding.root), DatabaseViewHolder {

    private var adapter: HorizontalSublistAdapter
    //Object that store adapter position
    private var constManager: StateHandler =
        EntryPoints.get(binding.root.context, ConstManagerInterface::class.java).getStateHandler()
    //Custom snap helper
    private val snapHelper: GravitySnapHelper = GravitySnapHelper(Gravity.CENTER)

    init {
        snapHelper.attachToRecyclerView(binding.recyclerView)
        binding.recyclerView.layoutManager = LinearLayoutManager(
            layoutInflater.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        adapter = HorizontalSublistAdapter(layoutInflater, listener)
        binding.recyclerView.adapter = adapter
    }

    override fun bind(cell: UserDatabaseContent) {
        cell as UserDatabaseContent.ContentList

        //Update list
        adapter.submitList(cell.content)

        //Set text from item
        binding.topicTextView.text = cell.topic

        //Restore state
        constManager.getViewHolderPosition(bindingAdapterPosition)?.let { position ->
            //Set restore position
            binding.recyclerView.scrollToPosition(position)
            //Set padding like SnapLinearHelper in recycler thread
            binding.recyclerView.post {
                val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
                val view = layoutManager.findViewByPosition(position) ?: return@post
                val array: IntArray = snapHelper.calculateDistanceToFinalSnap(layoutManager, view)
                if (array[0] != 0 || array[1] != 0) {
                    binding.recyclerView.scrollBy(array[0], array[1])
                }
            }
        }
    }

    /**
     * Save recycler view position
     */
    override fun saveState() {
        constManager.setViewHolderPosition(
            bindingAdapterPosition,
            getItemPosition()
        )
    }

    private fun getItemPosition(): Int {
        if (adapter.currentList.size == 0) return 0
        //each call of the function findFirstCompletelyVisibleItemPosition() returns a value 1 less,
        //because a piece of the previous element on the left is displayed on the screen,
        //that's why we add 1 to every call.
        return (binding.recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() + 1
    }
}
