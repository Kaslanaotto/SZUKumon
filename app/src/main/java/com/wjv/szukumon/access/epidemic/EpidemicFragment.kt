package com.wjv.szukumon.access.epidemic

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

class EpidemicFragment : Fragment() {

    companion object {
        fun newInstance() = EpidemicFragment()
    }

    private lateinit var viewModel: EpidemicViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_epidemic, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EpidemicViewModel::class.java)
        // TODO: Use the ViewModel

        val webView = view?.findViewById<WebView>(R.id.epidemic_web)
        val url = "http://www1.szu.edu.cn/NCP/iQRcode/"
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.setSupportZoom(true)
        webView?.settings?.builtInZoomControls = true
        webView?.settings?.displayZoomControls = false
        webView?.settings?.useWideViewPort = true
        webView?.settings?.loadWithOverviewMode = true
        webView?.settings?.textZoom = 100
        webView?.webViewClient = WebViewClient()
        webView?.loadUrl(url)

        val clipboard: ClipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val fab = view?.findViewById<FloatingActionButton>(R.id.epidemic_refresh)
        fab?.setOnClickListener {
            webView?.reload()
            val appContext = context?.applicationContext
            Toast.makeText(appContext, getString(R.string.refresh_hint), Toast.LENGTH_SHORT).show()
        }
        val fab2 = view?.findViewById<FloatingActionButton>(R.id.epidemic_back)
        fab2?.setOnClickListener {
            webView?.goBack()
        }
        val fab3 = view?.findViewById<FloatingActionButton>(R.id.epidemic_copy)
        fab3?.setOnClickListener {
            val clip: ClipData = ClipData.newPlainText("Current URL", webView?.url)
            clipboard.setPrimaryClip(clip)
            val appContext = context?.applicationContext
            Toast.makeText(appContext, getString(R.string.copy_hint), Toast.LENGTH_SHORT).show()
        }
    }
}