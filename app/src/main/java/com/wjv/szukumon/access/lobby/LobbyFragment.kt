package com.wjv.szukumon.access.lobby

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        val lobby_open = view?.findViewById<Button>(R.id.lobby_btn)

        lobby_open?.setOnClickListener {
            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            val content_url: Uri = Uri.parse("http://ehall.szu.edu.cn/new/index.html")
            intent.data = content_url
            startActivity(intent)
        }
    }
}