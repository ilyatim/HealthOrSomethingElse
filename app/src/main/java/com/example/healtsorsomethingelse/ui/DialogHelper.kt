package com.example.healtsorsomethingelse.ui

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.ui.dialog.RecipeBottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialog

object DialogHelper {

    fun showBottomSheetFoodDialog(activity: Activity) {
        val bottomSheetDialog = BottomSheetDialog(activity)
        val view = activity.layoutInflater.inflate(R.layout.recipe_bottom_sheet_dialog, null)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    fun showBottomSheetDialogFragment(supportFragmentManager: FragmentManager) {
        val bottomSheetDialog = RecipeBottomSheetDialog.newInstance()
        bottomSheetDialog.show(supportFragmentManager, "bottom sheet tag")
    }
}