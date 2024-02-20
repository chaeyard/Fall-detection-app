package com.example.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

// 메인화면
class MainActivity_signup2 : AppCompatActivity() {

    // 뷰가 생성되었을때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // XML뷰 파일을 연결시켜준다
        setContentView(R.layout.activity_main_signup2)
        settingButton()
        settingButton_back()

    }

    fun settingButton(){
        val button = findViewById<Button>(R.id.button_continue2)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_signup3::class.java)
            startActivity(intent)
        }
    }
    fun settingButton_back(){
        val button = findViewById<Button>(R.id.button_back)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_signup::class.java)
            startActivity(intent)
        }
    }


}
