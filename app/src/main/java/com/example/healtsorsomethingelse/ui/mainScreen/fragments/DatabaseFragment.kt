package com.example.healtsorsomethingelse.ui.mainScreen.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healtsorsomethingelse.R

class DatabaseFragment : Fragment() {

    companion object {
        fun newInstance() = DatabaseFragment()
    }

    private lateinit var viewModel: DatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.database_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}