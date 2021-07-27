package com.agilefreaks.freaks_catalog.features.freaks

import android.content.res.Configuration
import android.os.Bundle
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
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FragmentFreaksBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class FreaksFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreaksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_freaks, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        //val btShowSkills: Button = viewBinding.skillsBtn
        //val btShowProjects: Button = viewBinding.projectsBtn
        var activeFilter: String
        var btnList: List<Button> = listOf()
        //btnList = btnList.plus(btShowProjects)
        //btnList = btnList.plus(btShowSkills)

        for(item in btnList) {
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
                }
            }
        }


        // Initializes data
        val freaksList = loadFreaks()

        //Splits the screen in 2 columns if the device is in Portrait or in 3 columns otherwise
        val layoutManager = if(this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager(context, 2)
        } else
            GridLayoutManager(context, 3)
        val recyclerView = viewBinding.recycleView
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ItemAdapter (context, freaksList)

        return viewBinding.root
    }

    // Populates the freakList with dummy data
    private fun loadFreaks(): List<Freak> {
        return listOf(
            Freak("Ciprian","Hotea","Android Intern", "Full time", "Beginner", "Description1",0, listOf("Kotlin"), listOf("Freaks Catalog")),
            Freak("Andreea","Matei","Android Intern", "Full time", "Beginner", "Description2",0, listOf("Kotlin"), listOf("Freaks Catalog")),
            Freak("Robert","Solymosi","Android Intern", "Full time", "Beginner", "Description3",0, listOf("Kotlin"), listOf("Freaks Catalog")),
            Freak("Alexandra","Damaschin","Android Developer", "Full time", "Advanced", "Description4",0, listOf("Kotlin, iOS"), listOf("EPIX, ReAsig")),
            Freak("Denis","Coman","Android Developer", "Full time", "Advanced", "Description5",0, listOf("Kotlin, iOS"), listOf("EPIX, ReAsig")),
            Freak("Alexandru","Calinoiu","Android Developer", "Full time", "Advanced", "Description6",0, listOf("Kotlin, iOS"), listOf("EPIX, ReAsig")),
            Freak("Test1","Test11","Android Intern", "Full time", "Beginner", "Description3",0, listOf("Kotlin"), listOf("Freaks Catalog")),
            Freak("Test2","Test22","Android Developer", "Full time", "Advanced", "Description4",0, listOf("Kotlin, iOS"), listOf("EPIX, ReAsig")),
            Freak("Test3","Test33","Android Developer", "Full time", "Advanced", "Description5",0, listOf("Kotlin, iOS"), listOf("EPIX, ReAsig")),
//            Freak("Test4","Test44","Android Developer", "Full time", "Advanced", "Description6",0, listOf("Kotlin, iOS"), listOf("EPIX, ReAsig"))
        )
    }
}
