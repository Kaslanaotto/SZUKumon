package com.wjv.szukumon.access.kumon

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wjv.szukumon.R

class StudentsFragment : Fragment() {

    companion object {
        fun newInstance() = StudentsFragment()
    }

    private lateinit var viewModel: StudentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_students, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
        // TODO: Use the ViewModel

        val webView = view?.findViewById<WebView>(R.id.students_web)
        val url = "https://www1.szu.edu.cn/board/infolist.asp?infotype=%D1%A7%B9%A4"
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.setSupportZoom(true)
        webView?.settings?.builtInZoomControls = true
        webView?.settings?.displayZoomControls = false
        webView?.settings?.useWideViewPort = true
        webView?.settings?.loadWithOverviewMode = true
        webView?.settings?.textZoom = 150
        webView?.webViewClient = WebViewClient()
        webView?.loadUrl(url)

        val clipboard: ClipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val fab = view?.findViewById<FloatingActionButton>(R.id.students_refresh)
        fab?.setOnClickListener {
            webView?.reload()
            val appContext = context?.applicationContext
            Toast.makeText(appContext, getString(R.string.refresh_hint), Toast.LENGTH_SHORT).show()
        }
        val fab2 = view?.findViewById<FloatingActionButton>(R.id.students_back)
        fab2?.setOnClickListener {
            webView?.goBack()
        }
        val fab3 = view?.findViewById<FloatingActionButton>(R.id.students_copy)
        fab3?.setOnClickListener {
            val clip: ClipData = ClipData.newPlainText("Current URL", webView?.url)
            clipboard.setPrimaryClip(clip)
            val appContext = context?.applicationContext
            Toast.makeText(appContext, getString(R.string.copy_hint), Toast.LENGTH_SHORT).show()
        }
    }

}