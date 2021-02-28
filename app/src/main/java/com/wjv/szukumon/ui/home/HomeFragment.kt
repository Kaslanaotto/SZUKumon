package com.wjv.szukumon.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.wjv.szukumon.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.findViewById<Button>(R.id.button3)?.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nav_home_to_educationFragment)
        })

        view?.findViewById<Button>(R.id.button2)?.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nav_home_to_overheadFragment)
        })

        view?.findViewById<Button>(R.id.button1)?.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nav_home_to_latestFragment)
        })

        view?.findViewById<Button>(R.id.button4)?.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nav_home_to_lectureFragment)
        })

        view?.findViewById<Button>(R.id.button5)?.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nav_home_to_studentsFragment)
        })

        view?.findViewById<Button>(R.id.button6)?.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nav_home_to_researchFragment)
        })

        view?.findViewById<Button>(R.id.button7)?.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nav_home_to_policyFragment)
        })

        view?.findViewById<Button>(R.id.button8)?.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nav_home_to_campusFragment)
        })
    }
}