package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_login.*
//this activity is the "turned on" when a user wants to login into the app
//the onCreate function is only turned on when the whole activity was not visible and now it is.
//Using the savedInstanceState just allows the app to look back and see if this user already exists.
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginUsernameField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){

            }
            override fun afterTextChanged(p0: Editable?){

            }
        })
        //shows that this button which is named LoginButton will be visible
            loginButton.setOnClickListener{
                startActivity(Intent(this, MainActivity::class.java).apply {putExtra ( "username", loginUsernameField.text)})
            }
    }
}