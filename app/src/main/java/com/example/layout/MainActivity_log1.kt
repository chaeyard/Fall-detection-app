package com.example.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class MainActivity_log1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_log1)
        settingButtonok()
        tab()
    }

    fun settingButtonok(){
        val button = findViewById<RelativeLayout>(R.id.ghost_tab_2)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_log2::class.java)
            startActivity(intent)
        }
    }

    fun tab(){
        val tableLayout = findViewById<TableLayout>(R.id.table_1)
        val columnIndexDate = 0 // 날짜 열 인덱스
        val columnIndexTime = 1 // 시간 열 인덱스
        val columnIndexLocation = 2 // 위치 열 인덱스
        val columnIndexFallDetection = 3 // 낙상 감지 여부 열 인덱스

        val dlg = CustomPopupDialog(this)
        dlg.setOnOKClickedListener(object : CustomPopupDialog.OnClickedListener {


            override fun onOKClicked(msg: String) {
                // 이 곳에 필요한 동작을 추가하세요.
            }

            override fun onOKClicked() {
                // 현재 날짜와 시간을 가져오기 위한 Calendar 인스턴스 생성
                val calendar = Calendar.getInstance()

                // 현재 날짜와 시간을 Date 객체로 가져오기
                val currentDateAndTime: Date = calendar.time

                val sdf1 = SimpleDateFormat("yyyy-MM-dd")
                val sdf2 = SimpleDateFormat("HH:mm:ss")
                val formattedDateAndTime1: String = sdf1.format(currentDateAndTime)
                val formattedDateAndTime2: String = sdf2.format(currentDateAndTime)

                val newDate = formattedDateAndTime1
                val newTime = formattedDateAndTime2
                val newLocation = "새로운 위치"
                val newFallDetection = "낙상"

                // 변경할 내용을 준비합니다.

                // 변경할 행(row)을 가져옵니다.
                val rowIndexToChange = 1
                val rowToChange = tableLayout.getChildAt(rowIndexToChange) as TableRow

                // 각 열의 TextView를 가져옵니다.
                val dateTextView = rowToChange.getChildAt(columnIndexDate) as TextView
                val timeTextView = rowToChange.getChildAt(columnIndexTime) as TextView
                val locationTextView = rowToChange.getChildAt(columnIndexLocation) as TextView
                val fallDetectionTextView = rowToChange.getChildAt(columnIndexFallDetection) as TextView

                // 각 TextView의 text 속성을 변경합니다.
                dateTextView.text = newDate
                timeTextView.text = newTime
                locationTextView.text = newLocation
                fallDetectionTextView.text = newFallDetection
            }
        })

    }



}
