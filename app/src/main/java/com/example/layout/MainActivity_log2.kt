package com.example.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout

class MainActivity_log2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_log2)
        settingButtonok()
    }
    fun settingButtonok(){
        val button = findViewById<RelativeLayout>(R.id.ghost_tab_1)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_log1::class.java)
            startActivity(intent)
        }
    }
}