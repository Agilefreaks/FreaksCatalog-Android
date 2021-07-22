package com.agilefreaks.freaks_catalog.features.freaks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
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
            var checkedBoxes: List<Button> = mutableListOf()

            val cb: CheckBox? = view.findViewById(R.id.skill1)
            cb?.setOnClickListener{
                if(cb.isChecked) {
                    checkedBoxes.plus(cb)
                    Toast.makeText(context, checkedBoxes.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

        val btApply: Button? =  view?.findViewById(R.id.apply_btn)
        btApply?.setOnClickListener {

        }

        return viewBinding.root
    }

}
