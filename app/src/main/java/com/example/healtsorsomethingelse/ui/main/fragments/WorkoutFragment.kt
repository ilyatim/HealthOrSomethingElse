package com.example.healtsorsomethingelse.ui.main.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healtsorsomethingelse.utils.database.WorkoutViewModel
import com.example.healtsorsomethingelse.R

class WorkoutFragment : Fragment() {

    companion object {
        fun newInstance() = WorkoutFragment()
    }

    private lateinit var viewModel: WorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.categories_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        // TODO: Use the ViewModel
    }

}