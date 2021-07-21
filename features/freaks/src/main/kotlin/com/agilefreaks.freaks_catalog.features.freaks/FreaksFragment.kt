package com.agilefreaks.freaks_catalog.features.freaks

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FragmentFreaksBinding

class FreaksFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreaksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_freaks, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner

        val myDataset = loadFreaks()

        val recyclerView = viewBinding.recycleView

        val layoutManager = if(this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager(context, 2)
        } else
            GridLayoutManager(context, 3)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ItemAdapter (myDataset)

        return viewBinding.root
    }

    private fun loadFreaks(): List<Freak> {
        return listOf<Freak>(
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
