package com.example.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity_signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_signup)
        settingButton()
        settingButton_back()
    }
    fun settingButton(){
        val button = findViewById<Button>(R.id.button_continue)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_signup2::class.java)
            startActivity(intent)
        }
    }
    fun settingButton_back(){
        val button = findViewById<Button>(R.id.button_back)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_login::class.java)
            startActivity(intent)
        }
    }
}