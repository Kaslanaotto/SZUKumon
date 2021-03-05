package com.wjv.szukumon.others.feedback

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel

        val webView = view?.findViewById<WebView>(R.id.feedback_web)
        val url = "https://github.com/Kaslanaotto/SZUKumon/issues"
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.setSupportZoom(true)
        webView?.settings?.builtInZoomControls = true
        webView?.settings?.displayZoomControls = false
        webView?.settings?.javaScriptCanOpenWindowsAutomatically = true
        webView?.settings?.useWideViewPort = true
        webView?.settings?.loadWithOverviewMode = true
        webView?.settings?.textZoom = 100
        webView?.webViewClient = WebViewClient()
        webView?.webChromeClient = WebChromeClient()
        webView?.loadUrl(url)
        webView?.canGoBack()


        val clipboard: ClipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val fab = view?.findViewById<FloatingActionButton>(R.id.feedback_refresh)
        fab?.setOnClickListener {
            webView?.reload()
            val appContext = context?.applicationContext
            Toast.makeText(appContext, getString(R.string.refresh_hint), Toast.LENGTH_SHORT).show()
        }
        val fab2 = view?.findViewById<FloatingActionButton>(R.id.feedback_back)
        fab2?.setOnClickListener {
            webView?.goBack()
        }
        val fab3 = view?.findViewById<FloatingActionButton>(R.id.feedback_copy)
        fab3?.setOnClickListener {
            val clip: ClipData = ClipData.newPlainText("Current URL", webView?.url)
            clipboard.setPrimaryClip(clip)
            val appContext = context?.applicationContext
            Toast.makeText(appContext, getString(R.string.copy_hint), Toast.LENGTH_SHORT).show()
        }
    }
}