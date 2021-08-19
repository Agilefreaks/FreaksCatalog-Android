package com.agilefreaks.freaks_catalog.features.freaks

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agilefreaks.freaks_catalog.features.freaks.databinding.BottomSheetDialogBinding
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FragmentFreaksBinding
import com.agilefreaks.freaks_catalog.features.freaks.model.FilterItem
import com.agilefreaks.freaks_catalog.features.freaks.model.FilterViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.agilefreaks.freaks_catalog.features.freaks.repository.FreaksRepositoryImpl
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.pow
import kotlin.math.sqrt

class FreaksFragment : Fragment() {

    // private val viewModel: FreaksViewModel by inject()
    private val filterViewModel: FilterViewModel by inject()
    private val viewModel: FreaksViewModel by viewModel()
    private lateinit var viewBinding: FragmentFreaksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_freaks, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        viewBinding.freaksViewModel = viewModel
        viewBinding.filterViewModel = filterViewModel
        val isPortrait =
            this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        val layoutManager: RecyclerView.LayoutManager = when {
            !isPortrait && isTablet() -> GridLayoutManager(context, DISPLAY_IN_FOUR_COLUMNS)
            isPortrait && !isTablet() -> GridLayoutManager(context, DISPLAY_IN_TWO_COLUMNS)
            else -> GridLayoutManager(context, DISPLAY_IN_THREE_COLUMNS)
        }

        val recyclerView = viewBinding.recycleView
        recyclerView.layoutManager = layoutManager

        val showSkillsButton: Button = viewBinding.skillsBtn
        val showProjectsButton: Button = viewBinding.projectsBtn

        showSkillsButton.setOnClickListener {
            showSkillFilterModal()
        }
        showProjectsButton.setOnClickListener {
            showProjectsFilterViewModal()
        }

        viewModel.freaks.observe(viewLifecycleOwner, { freaks ->
            recyclerView.adapter = ItemAdapter(freaks) {
                onItemClicked(it.id)
            }
        })

        return viewBinding.root
    }

    private fun onItemClicked(freakId: String) {
        val action = FreaksFragmentDirections.actionFreaksToFreakDetails(freakId)
        findNavController().navigate(action)
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

    private fun showSkillFilterModal() {
        val dialog = setupFilterModal()

        filterViewModel.skills.observe(viewLifecycleOwner, {
            val adapter = FilterAdapter()
            adapter.submitList(it)
            val recyclerFiltersView = dialog.findViewById<RecyclerView>(R.id.recycler_filters_view)
            recyclerFiltersView.adapter = adapter
        })
    }

    private fun showProjectsFilterViewModal() {
        val dialog = setupFilterModal()

        filterViewModel.projects.observe(viewLifecycleOwner, {
            val adapter = FilterAdapter()
            adapter.submitList(it)
            val recyclerFiltersView = dialog.findViewById<RecyclerView>(R.id.recycler_filters_view)
            recyclerFiltersView.adapter = adapter
        })
    }

    private fun setupFilterModal() : View {
        val inflater = LayoutInflater.from(requireContext())
        val mBottomSheetBinding = BottomSheetDialogBinding.inflate(inflater, null, false)
        mBottomSheetBinding.viewModel = filterViewModel
        val dialog = BottomSheetDialog(requireContext())
        dialog.setCancelable(true)

        val btApply: Button? = mBottomSheetBinding.applyBtn
        val recyclerFiltersView =
            mBottomSheetBinding.recyclerFiltersView
        recyclerFiltersView?.layoutManager = LinearLayoutManager(requireContext())

        val dividerItemDecoration = DividerItemDecoration(
            recyclerFiltersView?.context,
            (recyclerFiltersView?.layoutManager as LinearLayoutManager).orientation
        )
        recyclerFiltersView?.addItemDecoration(dividerItemDecoration)

        dialog.setContentView(mBottomSheetBinding.root)
        dialog.show()

        btApply?.setOnClickListener {
        }

        return mBottomSheetBinding.root
    }

    companion object {
        private const val DISPLAY_IN_TWO_COLUMNS = 2
        private const val DISPLAY_IN_THREE_COLUMNS = 3
        private const val DISPLAY_IN_FOUR_COLUMNS = 4
        private const val MIN_TABLET_DISPLAY = 6.5
    }
}
