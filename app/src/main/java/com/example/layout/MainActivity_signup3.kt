package com.example.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity_signup3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_signup3)
        settingButton()
        settingButton_back()
    }
    fun settingButton(){
        val button = findViewById<Button>(R.id.button_register3)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_signup4::class.java)
            startActivity(intent)
        }
    }
    fun settingButton_back(){
        val button = findViewById<Button>(R.id.button_back3)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_signup2::class.java)
            startActivity(intent)
        }
    }


}