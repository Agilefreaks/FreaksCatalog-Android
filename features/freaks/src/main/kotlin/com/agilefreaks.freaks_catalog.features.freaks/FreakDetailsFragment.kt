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
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_freak_details, container, false)
        initBar()
        runBlocking {
            launch { getData() }
        }
        return viewBinding.root
    }

    fun initBar() {
        val mainActivitytoolBar = (activity as AppCompatActivity).supportActionBar
        mainActivitytoolBar?.setTitle(
            getString(
                R.string.name_template,
                viewModel.freakFirstName,
                viewModel.freakLastName
            )
        )
    }

    private suspend fun getData() {
        val freakId = arguments?.getString("freakId") ?: ""
        CoroutineScope(Dispatchers.IO).launch {
            val data = async { viewModel.getFreaksFromApi() }
            data.await()
            withContext(Dispatchers.Main) {
                viewModel.getData(freakId)
                showViews()
                initBar()
                viewBinding.freakDetailsViewModel = viewModel
                viewBinding.lifecycleOwner = viewLifecycleOwner
            }
        }
    }

    private fun showViews() {
        viewBinding.progressBarFreak.visibility = View.GONE
        viewBinding.cardViewFreak.visibility = View.VISIBLE
        viewBinding.imageView.visibility = View.VISIBLE
    }
}

