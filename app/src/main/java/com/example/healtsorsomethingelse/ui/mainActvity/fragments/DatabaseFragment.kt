package com.example.healtsorsomethingelse.ui.mainActvity.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.databinding.DatabaseFragmentBinding

class DatabaseFragment : Fragment() {

    private var _binding: DatabaseFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DatabaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DatabaseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}