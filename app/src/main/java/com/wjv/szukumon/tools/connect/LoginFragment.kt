package com.wjv.szukumon.tools.connect

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wjv.szukumon.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    lateinit var username: String
    lateinit var passwd: String

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val usernameEditText = view?.findViewById<EditText>(R.id.username)
        val passwordEditText = view?.findViewById<EditText>(R.id.password)
        //val prefs = activity?.getPreferences(Context.MODE_PRIVATE)
        //val username = prefs?.getString("username","")
        //val password = prefs?.getString("password","")

        username = usernameEditText?.text.toString()
        passwd = passwordEditText?.text.toString()

        outState.putString("username", username)
        outState.putString("password", passwd)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    interface APIService {
        @FormUrlEncoded
        @POST("/a70.htm?isReback=1")
        suspend fun createEmployee(@FieldMap params: HashMap<String?, String?>): Response<ResponseBody>
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val classloginButton = view.findViewById<Button>(R.id.classroom_login)
        val dormitoryloginButton = view.findViewById<Button>(R.id.dormitory_login)

        /*
        val rememberPass = view.findViewById<CheckBox>(R.id.rememberPass)
        val prefs = activity?.getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs?.getBoolean("remember_password",false)
        if(isRemember == true){
            rememberPass?.isChecked = true
        }
        */

        usernameEditText?.setText(savedInstanceState?.getString("username"))
        passwordEditText?.setText(savedInstanceState?.getString("password"))

        val handler = Handler()
        val autoClassLogin: Runnable = object : Runnable {
            override fun run() {
                val retrofit = Retrofit.Builder()
                        .baseUrl("https://drcom.szu.edu.cn")
                        .build()

                // Create Service
                val service = retrofit.create(APIService::class.java)

                val params = HashMap<String?, String?>()
                params["DDDDD"] = username
                params["upass"] = passwd
                params["R1"] = "0"
                params["R2"] = ""
                params["R6"] = "0"
                params["para"] = "00"
                params["0MKKey"] = "123456"
                params["buttonClicked"] = ""
                params["redirect_url"] = ""
                params["err_flag"] = ""
                params["username"] = ""
                params["password"] = ""
                params["user"] = ""
                params["cmd"] = ""
                params["Login"] = ""
                params["R7"] = "0"

                CoroutineScope(Dispatchers.IO).launch {
                    // Do the POST request and get response
                    val response = service.createEmployee(params)
                }
                handler.postDelayed(this, 10*1000)
            }
        }

        val autoDormitoryLogin: Runnable = object : Runnable {
            override fun run() {
                val studentid = username
                val pwd = passwd
                val urlf = "https://szu.szgalaxy.com.cn/NewFiPortal3/moportal/szuLogin.do?userIp=&userMac=&macType=1&resourceId=2&areaId=1&account="
                val urlb = "&pwd="
                val webView = view.findViewById<WebView>(R.id.backgound_web)

                webView?.webViewClient = WebViewClient()
                webView?.loadUrl(urlf.plus(studentid).plus(urlb).plus(pwd))

                handler.postDelayed(this, 10*1000)
            }
        }

        classloginButton.setOnClickListener {
            //loginViewModel.login(usernameEditText.text.toString(), passwordEditText.text.toString()

            username = usernameEditText.text.toString()
            passwd = passwordEditText.text.toString()
            /*
            val editor = prefs?.edit()
            if(rememberPass.isChecked){
                editor?.putBoolean("remember_password", true)
                editor?.putString("username", username)
                editor?.putString("password", passwd)
            } else {
                editor?.clear()
            }
            */
            handler.post(autoClassLogin)

            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            val content_url: Uri = Uri.parse("https://www.baidu.com")
            intent.data = content_url
            startActivity(intent)
        }

        dormitoryloginButton.setOnClickListener {

            username = usernameEditText.text.toString()
            passwd = passwordEditText.text.toString()
            /*
            val editor = prefs?.edit()
            if(rememberPass.isChecked){
                editor?.putBoolean("remember_password", true)
                editor?.putString("username", username)
                editor?.putString("password", passwd)
            } else {
                editor?.clear()
            }
            */
            handler.post(autoDormitoryLogin)

            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            val content_url: Uri = Uri.parse("https://www.baidu.com")
            intent.data = content_url
            startActivity(intent)
        }
    }
}