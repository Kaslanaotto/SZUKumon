package com.wjv.szukumon.ui.kumon

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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wjv.szukumon.R

class ResearchFragment : Fragment() {

    companion object {
        fun newInstance() = ResearchFragment()
    }

    private lateinit var viewModel: ResearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_research, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ResearchViewModel::class.java)
        // TODO: Use the ViewModel

        val webView = view?.findViewById<WebView>(R.id.research_web)
        val url = "https://www1.szu.edu.cn/board/infolist.asp?infotype=%BF%C6%D1%D0"
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
        val fab = view?.findViewById<FloatingActionButton>(R.id.research_refresh)
        fab?.setOnClickListener {
            webView?.reload()
        }
        val fab2 = view?.findViewById<FloatingActionButton>(R.id.research_back)
        fab2?.setOnClickListener {
            webView?.goBack()
        }
        val fab3 = view?.findViewById<FloatingActionButton>(R.id.research_copy)
        fab3?.setOnClickListener {
            val clip: ClipData = ClipData.newPlainText("Current URL", webView?.url)
            clipboard.setPrimaryClip(clip)
        }
    }

}