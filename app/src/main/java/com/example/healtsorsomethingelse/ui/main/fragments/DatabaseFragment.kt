package com.example.healtsorsomethingelse.ui.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.databinding.DatabaseFragmentBinding
import com.example.healtsorsomethingelse.ui.main.vpComponents.FragmentAdapter
import com.example.healtsorsomethingelse.utils.CustomOnTabSelectedListener
import com.example.healtsorsomethingelse.utils.database.DatabaseViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialSharedAxis

class DatabaseFragment : Fragment() {

    private var _binding: DatabaseFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DatabaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DatabaseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*binding.viewPager.adapter = FragmentAdapter(this.requireActivity())
        binding.appBarLayout.outlineProvider = null
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
        binding.viewPager.isUserInputEnabled = false
        binding.tabLayout.addOnTabSelectedListener(object : CustomOnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = (tab?.position ?: return)
            }
        })*/

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

