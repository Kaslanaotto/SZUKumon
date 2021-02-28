package com.wjv.szukumon.ui.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wjv.szukumon.R

class FeedbackFragment : Fragment() {

    private lateinit var feedbackViewModel: FeedbackViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        feedbackViewModel =
                ViewModelProvider(this).get(FeedbackViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_feedback, container, false)
        return root
    }
}