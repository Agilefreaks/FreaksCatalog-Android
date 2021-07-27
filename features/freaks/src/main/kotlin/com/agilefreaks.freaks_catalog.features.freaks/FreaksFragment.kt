package com.agilefreaks.freaks_catalog.features.freaks

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FragmentFreaksBinding
import kotlin.math.pow
import kotlin.math.sqrt
import com.google.android.material.bottomsheet.BottomSheetDialog

class FreaksFragment : Fragment() {
    companion object {
        private const val DISPLAY_IN_TWO_COLUMNS = 2
        private const val DISPLAY_IN_THREE_COLUMNS = 3
        private const val DISPLAY_IN_FOUR_COLUMNS = 4
        private const val MIN_TABLET_DISPLAY = 6.5
        private const val FREAKS_COUNT = 10
    }

    private lateinit var viewBinding: FragmentFreaksBinding

    @SuppressLint("InflateParams")
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
        val btShow: Button = viewBinding.skillsBtn
        btShow.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
            dialog.setCancelable(true)
            dialog.setContentView(view)
            dialog.show()
            var checkedBoxes: List<CheckBox> = listOf()

            val btShowSkills: Button = viewBinding.skillsBtn
            val btShowProjects: Button = viewBinding.projectsBtn
            var activeFilter: String
            var btnList: List<Button> = listOf()
            btnList = btnList.plus(btShowProjects)
            btnList = btnList.plus(btShowSkills)

            for (item in btnList) {
                item.setOnClickListener {
                    activeFilter = item.toString()
                    val dialog = BottomSheetDialog(requireContext())
                    val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
                    dialog.setCancelable(true)
                    dialog.setContentView(view)
                    dialog.show()
                    var checkedBoxes: List<CheckBox> = listOf()
                    val cb1: CheckBox = view.findViewById(R.id.skill1)
                    val cb2: CheckBox = view.findViewById(R.id.skill2)
                    val cb3: CheckBox = view.findViewById(R.id.skill3)
                    val cb4: CheckBox = view.findViewById(R.id.skill4)

                    var allCheckBoxes: List<CheckBox> = listOf()
                    allCheckBoxes = allCheckBoxes.plus(cb1)
                    allCheckBoxes = allCheckBoxes.plus(cb2)
                    allCheckBoxes = allCheckBoxes.plus(cb3)
                    allCheckBoxes = allCheckBoxes.plus(cb4)

                    for (item in allCheckBoxes) {
                        item.setOnClickListener {
                            if (item.isChecked) {
                                checkedBoxes = checkedBoxes.plus(item)
                            }
                        }
                    }

                    val btReset: TextView? = view?.findViewById(R.id.reset)
                    btReset?.setOnClickListener {
                        Log.d("Testing", checkedBoxes.first().toString())
                        checkedBoxes.forEach {
                            if (it.isChecked) {
                                it.toggle()
                            }
                        }
                        checkedBoxes = listOf()
                    }

                    val btApply: Button? = view?.findViewById(R.id.apply_btn)
                    btApply?.setOnClickListener {
                        checkedBoxes = listOf()
                        var activeFilter: String
                    }
                }
            }


            // Initializes data
            val freaksList = loadFreaks()

            //Splits the screen in 2 columns if the device is in Portrait or in 3 columns otherwise
            val layoutManager =
                if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    GridLayoutManager(context, 2)
                } else
                    GridLayoutManager(context, 3)
            val recyclerView = viewBinding.recycleView
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = ItemAdapter(freaksList)
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
}
