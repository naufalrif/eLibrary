package com.example.elibrary.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.elibrary.R
import com.example.elibrary.room.user.User
import com.example.elibrary.room.user.UserDB
import com.example.elibrary.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private var userdb : UserDB? = null
    lateinit var viewmodel: ViewModelUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userdb = UserDB.getInstance(this)

        viewmodel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModelUser::class.java)

        btn_reg.setOnClickListener {
            val username = et_reg_username.text.toString()
            val pass = et_reg_pass.text.toString()
            val pass2 = et_reg_pass_repeat.text.toString()
            val email = et_reg_email.text.toString()
            if (pass == pass2){
                GlobalScope.launch {
                    viewmodel.registerLive(User(null,username,email,pass))
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Berhasil", Toast.LENGTH_LONG).show()
                        startActivity(Intent(applicationContext,LoginActivity::class.java))
                    }
                }
            }
        }
    }
}