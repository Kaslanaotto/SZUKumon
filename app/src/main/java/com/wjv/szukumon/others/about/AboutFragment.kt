package com.wjv.szukumon.others.about

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
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
            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            val content_url: Uri = Uri.parse("https://github.com/Kaslanaotto/SZUKumon")
            intent.data = content_url
            startActivity(intent)
        }
        val btnUpdate = view?.findViewById<Button>(R.id.button)
        btnUpdate?.setOnClickListener {
            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            val content_url: Uri = Uri.parse("https://github.com/Kaslanaotto/SZUKumon/releases/latest")
            intent.data = content_url
            startActivity(intent)
        }
    }
}