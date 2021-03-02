package com.wjv.szukumon.ui.connect

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.wjv.szukumon.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.DataOutputStream

import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    interface APIService {
        // ...

        @POST("/api/v1/create")
        suspend fun createEmployee(@Body requestBody: RequestBody): Response<ResponseBody>

        // ...
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val classloginButton = view.findViewById<Button>(R.id.classroom_login)
        val dormitoryloginButton = view.findViewById<Button>(R.id.dormitory_login)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loading)

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                // ignore
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            false
        }


        classloginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            //loginViewModel.login(usernameEditText.text.toString(), passwordEditText.text.toString())

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://drcom.szu.edu.cn/a70.htm?isReback=1")
                    .build()

            // Create Service
            val service = retrofit.create(APIService::class.java)

            // Create JSON using JSONObject
            val jsonObject = JSONObject()
            jsonObject.put("DDDDD", usernameEditText.text.toString())
            jsonObject.put("upass", passwordEditText.text.toString())
            jsonObject.put("R1", "0")
            jsonObject.put("R2", "")
            jsonObject.put("R6", "0")
            jsonObject.put("para", "00")
            jsonObject.put("0MKKey", "123456")
            jsonObject.put("buttonClicked", "")
            jsonObject.put("redirect_url", "")
            jsonObject.put("err_flag", "")
            jsonObject.put("username", "")
            jsonObject.put("password", "")
            jsonObject.put("user", "")
            jsonObject.put("cmd", "")
            jsonObject.put("Login", "")
            jsonObject.put("R7", "0")

            // Convert JSONObject to String
            val jsonObjectString = jsonObject.toString()

            // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

            CoroutineScope(Dispatchers.IO).launch {
                // Do the POST request and get response
                val response = service.createEmployee(requestBody)
            }

        }

        dormitoryloginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE

            val studentid = usernameEditText.text.toString()
            val pwd = passwordEditText.text.toString()
            val urlf = "https://szu.szgalaxy.com.cn/NewFiPortal3/moportal/szuLogin.do?userIp=&userMac=&macType=1&resourceId=2&areaId=1&account="
            val urlb = "&pwd="
            val webView = view.findViewById<WebView>(R.id.backgound_web)

            webView?.loadUrl(urlf.plus(studentid).plus(urlb).plus(pwd))
        }
    }
}