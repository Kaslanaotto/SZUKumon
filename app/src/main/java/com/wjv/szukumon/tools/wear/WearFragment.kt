package com.wjv.szukumon.tools.wear

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wjv.szukumon.R

class WearFragment : Fragment() {

    companion object {
        fun newInstance() = WearFragment()
    }

    private lateinit var viewModel: WearViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wear, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WearViewModel::class.java)
        // TODO: Use the ViewModel
    }

}