package com.agilefreaks.freaks_catalog.features.portofolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.agilefreaks.freaks_catalog.features.portofolio.databinding.FragmentPortofolioBinding

class PortofolioFragment : Fragment() {
    private lateinit var viewBinding: FragmentPortofolioBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_portofolio, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }
}
