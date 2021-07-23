package com.agilefreaks.freaks_catalog.features.freaks

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FragmentFreaksBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class FreaksFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreaksBinding

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_freaks, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        val btShow: Button = viewBinding.skillsBtn
        btShow.setOnClickListener{
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
                    if (item.isChecked){
                        checkedBoxes = checkedBoxes.plus(item)
                    }
                }
            }

            val btReset: TextView? = view?.findViewById(R.id.reset)
            btReset?.setOnClickListener {
                Log.d("Testing", checkedBoxes.first().toString())
                checkedBoxes.forEach{
                    if (it.isChecked){
                        it.toggle()

                    }
                }
                checkedBoxes = listOf()
            }

            val btApply: Button? =  view?.findViewById(R.id.apply_btn)
            btApply?.setOnClickListener {
                checkedBoxes = listOf()
            }
        }

        return viewBinding.root
    }
}
