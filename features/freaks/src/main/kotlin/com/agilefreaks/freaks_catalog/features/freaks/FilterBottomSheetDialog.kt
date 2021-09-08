package com.agilefreaks.freaks_catalog.features.freaks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.agilefreaks.freaks_catalog.features.freaks.FreaksFragment.Companion.PROJECTS
import com.agilefreaks.freaks_catalog.features.freaks.FreaksFragment.Companion.SKILLS
import com.agilefreaks.freaks_catalog.features.freaks.databinding.BottomSheetDialogBinding
import com.agilefreaks.freaks_catalog.features.freaks.filter.FilterAdapter
import com.agilefreaks.freaks_catalog.features.freaks.model.FilterItem
import com.agilefreaks.freaks_catalog.features.freaks.model.Skill
import com.google.android.material.bottomsheet.BottomSheetDialog

class FilterBottomSheetDialog(
    private val list: List<FilterItem>,
    private val mContext: Context,
    private val onApplyFilters: () -> Unit,
    private val onResetFilters: (String) -> Unit
) : BottomSheetDialog(mContext) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setupFilterModal(): View {
        val inflater = LayoutInflater.from(mContext)
        val bottomSheetBinding = BottomSheetDialogBinding.inflate(inflater, null, false)
        val titleText: String = if(list.first() is Skill){
            SKILLS
        } else {
            PROJECTS
        }
        bottomSheetBinding.filterTitle.text = titleText
        bottomSheetBinding.recyclerFiltersView.layoutManager =
            LinearLayoutManager(mContext)

        val adapter = FilterAdapter()
        adapter.submitList(list)

        val recyclerFiltersView = bottomSheetBinding.recyclerFiltersView
        recyclerFiltersView.adapter = adapter

        bottomSheetBinding.applyButton.setOnClickListener {
            onApplyFilters()

            this.cancel()
        }
        bottomSheetBinding.reset.setOnClickListener {
            onResetFilters(titleText)
        }
        return bottomSheetBinding.root
    }
}
