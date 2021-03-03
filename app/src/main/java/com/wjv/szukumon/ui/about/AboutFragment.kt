package com.wjv.szukumon.ui.about

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wjv.szukumon.R

class AboutFragment : Fragment() {

    private lateinit var slideshowViewModel: AboutViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProvider(this).get(AboutViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_about, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val clipboard: ClipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val fab = view?.findViewById<FloatingActionButton>(R.id.fab_contact)
        fab?.setOnClickListener {
            val clip: ClipData = ClipData.newPlainText("WeChat_ID", "wjv_0510ln")
            clipboard.setPrimaryClip(clip)
            val appContext = context?.applicationContext
            Toast.makeText(appContext, "WeChat_ID: wjv_0510ln copied!", Toast.LENGTH_LONG).show()
        }

        val btn_github = view?.findViewById<Button>(R.id.button_github)
        btn_github?.setOnClickListener {
            val clip: ClipData = ClipData.newPlainText("Github", "https://github.com/Kaslanaotto/SZUKumon")
            clipboard.setPrimaryClip(clip)
            val appContext = context?.applicationContext
            Toast.makeText(appContext, getString(R.string.copy_hint), Toast.LENGTH_SHORT).show()
        }
        val btnUpdate = view?.findViewById<Button>(R.id.button)
        btnUpdate?.setOnClickListener {
            val clip: ClipData = ClipData.newPlainText("Link", "链接:https://pan.baidu.com/s/17YMKNoGFBf7c16VY-RUIlg 提取码:6p1m")
            clipboard.setPrimaryClip(clip)
            val appContext = context?.applicationContext
            Toast.makeText(appContext, "打开手机百度网盘查看分享", Toast.LENGTH_LONG).show()
        }
    }
}