package com.wjv.szukumon.ui.connect

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wjv.szukumon.R

class ConnectFragment : Fragment() {

    companion object {
        fun newInstance() = ConnectFragment()
    }

    private lateinit var viewModel: ConnectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_connect, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConnectViewModel::class.java)
        // TODO: Use the ViewModel
    }

}