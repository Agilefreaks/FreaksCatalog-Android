package com.agilefreaks.freaks_catalog.features.freaks

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
            //val intent = Intent(context, TestActivity::class.java)
            //startActivity(intent)
            val dialog = BottomSheetDialog(requireContext())

            // on below line we are inflating a layout file which we have created.
            var view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)

            // on below line we are creating a variable for our button
            // which we are using to dismiss our dialog.

            // below line is use to set cancelable to avoid
            // closing of dialog box when clicking on the screen.
            dialog.setCancelable(false)

            // on below line we are setting
            // content view to our view.
            dialog.setContentView(view)


            // on below line we are calling
            // a show method to display a dialog.
            dialog.show()

            val cb: CheckBox? = view.findViewById(R.id.skill1)
            Log.d("Testing", cb.toString())
            cb?.setOnClickListener{
                if(cb.isChecked) Toast.makeText(this.context, "Merge", Toast.LENGTH_SHORT).show()
            }
        }



        return viewBinding.root
    }

    //private fun onClick(){
       // val bottomSheetDialog = BottomSheetDialog(requireContext())
       // bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog)

    //}

}
