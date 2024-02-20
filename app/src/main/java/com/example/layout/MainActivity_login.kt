package com.example.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)
        settingButton()
        settingButtonok()


    }

    fun settingButton(){
        val button = findViewById<Button>(R.id.button_makeid)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_signup::class.java)
            startActivity(intent)
        }
    }
    fun settingButtonok(){
        val button = findViewById<Button>(R.id.button_ok)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_my::class.java)
            startActivity(intent)
        }
    }
}