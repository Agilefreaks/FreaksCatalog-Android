package com.agilefreaks.freaks_catalog.features.freaks

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FragmentFreaksBinding
import com.google.gson.GsonBuilder
import java.io.*
import java.util.*


class FreaksFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreaksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_freaks, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner

        // Initializes data
        val freaksList = loadFreaksFromJson()

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

    // Populate the freakList from a JSON file
    private fun loadFreaksFromJson(): List<Freak> {
        val inputStream: InputStream = resources.openRawResource(R.raw.dummy_data)
        val inputReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputReader)
        val inputString = bufferedReader.use { it.readText() }

        val gson2 = GsonBuilder().create()
        return gson2.fromJson(inputString, Array<Freak>::class.java).toList()
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
