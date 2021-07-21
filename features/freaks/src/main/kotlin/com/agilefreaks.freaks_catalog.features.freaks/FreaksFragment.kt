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
import android.widget.Toast
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
