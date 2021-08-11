package com.agilefreaks.freaks_catalog.features.freaks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.agilefreaks.freaks_catalog.features.freaks.databinding.FragmentFreakDetailsBinding

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
        viewModel.setID("3")
        return viewBinding.root
    }

    fun initBar() {
        val mainActivitytoolBar = (activity as AppCompatActivity).supportActionBar
        mainActivitytoolBar?.setTitle(getString(R.string.name_template, viewModel.firstName, viewModel.lastName))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.viewModel = viewModel

    }
}

