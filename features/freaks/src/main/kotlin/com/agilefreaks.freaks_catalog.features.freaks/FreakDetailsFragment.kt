package com.agilefreaks.freaks_catalog.features.freaks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FragmentFreakDetailsBinding
import kotlinx.coroutines.*

class FreakDetailsFragment : Fragment() {
    private lateinit var viewBinding: FragmentFreakDetailsBinding

    private val viewModel: DetailsViewModel by viewModels {
        FreaksDetailsViewModelFactory(FreaksRepositoryImpl(ApolloDataSource()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAppBarTitle("")

        listenToEvents()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_freak_details, container, false)

        viewBinding.freakDetailsViewModel = viewModel

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val freakId = arguments?.getString("freakId") ?: ""

        viewModel.loadFreak(freakId)
    }

    private fun listenToEvents(){
        viewModel.freak.observe(this, {
            val freakName = it.firstName + " " + it.lastName

            setAppBarTitle(freakName)
        })
    }

    private fun setAppBarTitle(title: String) {
        val mainActivityToolBar = (activity as AppCompatActivity).supportActionBar

        mainActivityToolBar?.title = title
    }

}

