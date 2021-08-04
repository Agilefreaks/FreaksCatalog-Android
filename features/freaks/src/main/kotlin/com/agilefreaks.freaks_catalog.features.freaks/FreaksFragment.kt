package com.agilefreaks.freaks_catalog.features.freaks

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FragmentFreaksBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.math.pow
import kotlin.math.sqrt

class FreaksFragment : Fragment() {
    companion object {
        private const val DISPLAY_IN_TWO_COLUMNS = 2
        private const val DISPLAY_IN_THREE_COLUMNS = 3
        private const val DISPLAY_IN_FOUR_COLUMNS = 4
        private const val MIN_TABLET_DISPLAY = 6.5
        private const val FREAKS_COUNT = 10
    }

    private lateinit var viewBinding: FragmentFreaksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_freaks, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner

        val freaksList = loadFreaks()

        val isPortrait =
            this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        val layoutManager: RecyclerView.LayoutManager = when {
            !isPortrait && isTablet() -> GridLayoutManager(context, DISPLAY_IN_FOUR_COLUMNS)
            isPortrait -> GridLayoutManager(context, DISPLAY_IN_TWO_COLUMNS)
            else -> GridLayoutManager(context, DISPLAY_IN_THREE_COLUMNS)
        }

        val recyclerView = viewBinding.recycleView
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ItemAdapter(freaksList)

        val btShowSkills: Button = viewBinding.skillsBtn
        val btShowProjects: Button = viewBinding.projectsBtn

        btShowSkills.setOnClickListener {

            showFilterModal("Skills", null)
        }
        btShowProjects.setOnClickListener {
            showFilterModal("Projects", null)
        }

        return viewBinding.root
    }

    private fun isTablet(): Boolean {
        val metrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context?.display?.getRealMetrics(metrics)
        } else {
            activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        }

        val widthInches = metrics.widthPixels / metrics.xdpi
        val heightInches = metrics.heightPixels / metrics.ydpi
        val diagonalInches =
            sqrt(widthInches.toDouble().pow(2.0) + heightInches.toDouble().pow(2.0))
        return diagonalInches >= MIN_TABLET_DISPLAY
    }

    private fun loadFreaks(): List<Freak> {
        val freak = Freak(
            "Ciprian",
            "Hotea",
            "Android Intern",
            "Full Time",
            "Beginner",
            "Description",
            0,
            listOf("Kotlin"),
            listOf("Freaks Catalog")
        )
        return mutableListOf<Freak>().apply {
            repeat(FREAKS_COUNT) { this.add(freak) }
        }
    }

    private fun loadFilters(activeFilter: String) =
        if (activeFilter == "Skills") {
            listOf<String>("Android", "Kotlin", "Other Skill", "iOS")
        } else {
            listOf("Freaks Catalog", "Proj2", "Tutorial", "Altkeva")
        }

    private fun showFilterModal(activeFilter: String, otherView: ViewGroup?){
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, otherView)
        dialog.setCancelable(true)
        val filterTitle: TextView? = view.findViewById(R.id.filter_title)
        filterTitle?.text = activeFilter
        val btReset: TextView? = view.findViewById(R.id.reset)
        val btApply: Button? = view.findViewById(R.id.apply_btn)
        val recyclerFiltersView = view?.findViewById<RecyclerView>(R.id.recycler_filters_view)

        val filtersList = loadFilters(activeFilter)
        recyclerFiltersView?.layoutManager = LinearLayoutManager(view.context)

        val dividerItemDecoration = DividerItemDecoration(
            recyclerFiltersView?.getContext(),
            (recyclerFiltersView?.layoutManager as LinearLayoutManager).getOrientation()
        )
        recyclerFiltersView?.addItemDecoration(dividerItemDecoration)
        val myAdapter = FilterAdapter(filtersList)

        recyclerFiltersView?.adapter = myAdapter
        dialog.setContentView(view)
        dialog.show()
        btReset?.setOnClickListener {
            myAdapter.resetCheckboxes()
        }

        btApply?.setOnClickListener {

        }
    }

}
