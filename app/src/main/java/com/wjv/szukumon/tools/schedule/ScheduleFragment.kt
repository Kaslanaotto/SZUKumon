package com.wjv.szukumon.tools.schedule

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.http.SslError
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wjv.szukumon.BuildConfig
import com.wjv.szukumon.R

class ScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        // TODO: Use the ViewModel

        val webView = view?.findViewById<WebView>(R.id.schedule_web)
        val url = "http://ehall.szu.edu.cn/appShow?appId=4770397878132218"
        webView?.settings?.javaScriptEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView?.settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        //webView?.addJavascriptInterface(InJavaScriptLocalObj(), "local_obj")
        webView?.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                handler.proceed() //接受所有网站的证书
                return
            }
        }
        webView?.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }
        }
        // 设置自适应屏幕，两者合用
        webView?.settings?.useWideViewPort = true //将图片调整到适合WebView的大小
        webView?.settings?.loadWithOverviewMode = true // 缩放至屏幕的大小
        // 缩放操作
        webView?.settings?.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。
        webView?.settings?.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
        webView?.settings?.displayZoomControls = false //隐藏原生的缩放控件wvCourse.settings
        webView?.settings?.javaScriptCanOpenWindowsAutomatically = true
        webView?.settings?.domStorageEnabled = true
        webView?.settings?.userAgentString = webView?.settings?.userAgentString?.replace("Mobile", "eliboM")?.replace("Android", "diordnA")
        webView?.settings?.textZoom = 100
        webView?.loadUrl(url)
        webView?.canGoBack()


        val clipboard: ClipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val fab = view?.findViewById<FloatingActionButton>(R.id.schedule_refresh)
        fab?.setOnClickListener {
            webView?.reload()
            val appContext = context?.applicationContext
            Toast.makeText(appContext, getString(R.string.refresh_hint), Toast.LENGTH_SHORT).show()
        }
        val fab2 = view?.findViewById<FloatingActionButton>(R.id.schedule_ics)
        fab2?.setOnClickListener {
            val appContext = context?.applicationContext
            Toast.makeText(appContext, "敬请期待", Toast.LENGTH_SHORT).show()
        }
        val fab3 = view?.findViewById<FloatingActionButton>(R.id.schedule_copy)
        fab3?.setOnClickListener {
            val clip: ClipData = ClipData.newPlainText("Current URL", webView?.url)
            clipboard.setPrimaryClip(clip)
            val appContext = context?.applicationContext
            Toast.makeText(appContext, getString(R.string.copy_hint), Toast.LENGTH_SHORT).show()
        }
    }

}