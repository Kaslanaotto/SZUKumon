package com.wjv.szukumon.ui.lobby

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wjv.szukumon.R

class LobbyFragment : Fragment() {

    private lateinit var lobbyViewModel: LobbyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lobbyViewModel =
            ViewModelProvider(this).get(LobbyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_lobby, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val webView = view?.findViewById<WebView>(R.id.lobby_web)
        val url = "http://ehall.szu.edu.cn/new/index.html"
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.setSupportZoom(true)
        webView?.settings?.builtInZoomControls = true
        webView?.settings?.displayZoomControls = false
        webView?.settings?.useWideViewPort = true
        webView?.settings?.loadWithOverviewMode = true
        webView?.settings?.textZoom = 100
        webView?.settings?.blockNetworkImage = true
        webView?.settings?.blockNetworkLoads = true
        webView?.settings?.domStorageEnabled = true
        webView?.settings?.allowFileAccess = true
        webView?.settings?.allowContentAccess = true
        webView?.settings?.mixedContentMode = MIXED_CONTENT_ALWAYS_ALLOW
        webView?.webChromeClient = WebChromeClient()
        webView?.loadUrl(url)

        val clipboard: ClipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val fab = view?.findViewById<FloatingActionButton>(R.id.lobby_refresh)
        fab?.setOnClickListener {
            webView?.reload()
        }
        val fab2 = view?.findViewById<FloatingActionButton>(R.id.lobby_back)
        fab2?.setOnClickListener {
            webView?.goBack()
        }
        val fab3 = view?.findViewById<FloatingActionButton>(R.id.lobby_copy)
        fab3?.setOnClickListener {
            val clip: ClipData = ClipData.newPlainText("Current URL", webView?.url)
            clipboard.setPrimaryClip(clip)
        }
    }
}