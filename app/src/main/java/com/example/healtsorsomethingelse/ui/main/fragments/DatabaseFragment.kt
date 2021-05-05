package com.example.healtsorsomethingelse.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.healtsorsomethingelse.databinding.DatabaseFragmentBinding
import com.example.healtsorsomethingelse.utils.DatabaseViewModel

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