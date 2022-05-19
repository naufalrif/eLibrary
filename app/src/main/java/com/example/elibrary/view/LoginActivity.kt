package com.example.elibrary.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.elibrary.R
import com.example.elibrary.manager.UserManager
import com.example.elibrary.room.user.UserDB
import com.example.elibrary.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    var userdb : UserDB? = null
    lateinit var userManager: UserManager
    lateinit var viewmodel : ViewModelUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userManager = UserManager(this)

        userdb = UserDB.getInstance(this)

        viewmodel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModelUser::class.java)

        doLogin()
        toRegister()
    }

    fun doLogin(){
        btn_login.setOnClickListener {
            var username = et_username.text.toString()
            var pass = et_pass.text.toString()
            if(username.isNotEmpty() && pass.isNotEmpty()){
                viewmodel.cekdata.observe(this, Observer {
                    if(it != 0){
                        Toast.makeText(this, "Login sukses!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,HomeActivity::class.java))
                        loginDataStore(username,pass)
                        finish()
                    }else{
                        Toast.makeText(this, "Email atau password salah!", Toast.LENGTH_LONG)
                            .show()
                    }
                })
                viewmodel.loginLive(username,pass)
            }else{
                Toast.makeText(this,"Data masih kosong!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun toRegister(){
        tv_register.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    fun loginDataStore(username : String, password : String){
        GlobalScope.launch {
            userManager.login(username, password)
            userManager.setStatus("yes")
        }
    }
}