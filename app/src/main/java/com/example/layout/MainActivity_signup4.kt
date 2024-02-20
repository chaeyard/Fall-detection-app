package com.example.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity_signup4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_signup4)
        settingButton_back()
        settingButton_reg()
    }

    fun settingButton_back(){
        val button = findViewById<Button>(R.id.button_back4)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_signup3::class.java)
            startActivity(intent)
        }
    }
    fun settingButton_reg(){
        val button = findViewById<Button>(R.id.button_register4)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_login::class.java)
            startActivity(intent)
        }
    }
}