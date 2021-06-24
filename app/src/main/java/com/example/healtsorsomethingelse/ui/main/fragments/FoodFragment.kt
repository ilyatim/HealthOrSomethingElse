package com.example.healtsorsomethingelse.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.healtsorsomethingelse.databinding.RecipesFragmentBinding
import com.example.healtsorsomethingelse.ui.main.vpComponents.RecipesFragmentAdapter
import com.example.healtsorsomethingelse.utils.BaseFragment
import com.example.healtsorsomethingelse.utils.CustomOnTabSelectedListener
import com.google.android.material.tabs.TabLayout

class FoodFragment : BaseFragment() {
    private var _binding: RecipesFragmentBinding? = null
    private val binding: RecipesFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecipesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewPager.adapter = RecipesFragmentAdapter(this.requireActivity())
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
        binding.tabLayout.addOnTabSelectedListener(object : CustomOnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = (tab?.position ?: return)
            }
        })
        binding.appBarLayout.outlineProvider = null
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = FoodFragment()
    }
}
