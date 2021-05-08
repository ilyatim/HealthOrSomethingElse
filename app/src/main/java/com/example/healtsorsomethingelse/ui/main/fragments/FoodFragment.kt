package com.example.healtsorsomethingelse.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.database.RecipesType
import com.example.healtsorsomethingelse.data.database.RecipeCell
import com.example.healtsorsomethingelse.data.database.UiAction
import com.example.healtsorsomethingelse.data.database.UiState
import com.example.healtsorsomethingelse.utils.database.FoodViewModel
import com.example.healtsorsomethingelse.databinding.RecipesFragmentBinding
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
import com.example.healtsorsomethingelse.extensions.ViewExtensions.visible
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database.OnRecipeItemListener
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database.RecipesAdapter
import com.example.healtsorsomethingelse.ui.main.vpComponents.FragmentAdapter
import com.example.healtsorsomethingelse.ui.main.vpComponents.RecipesFragmentAdapter
import com.example.healtsorsomethingelse.utils.BaseFragment
import com.example.healtsorsomethingelse.utils.CustomOnTabSelectedListener
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
