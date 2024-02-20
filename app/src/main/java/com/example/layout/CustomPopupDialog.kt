package com.example.layout

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CustomPopupDialog(private val ctx: Context) {

    private val dlg = Dialog(ctx)

    interface OnClickedListener {
        fun onOKClicked(msg: String)
        fun onOKClicked()
    }

    private lateinit var listener: OnClickedListener

    fun setOnOKClickedListener(listener: OnClickedListener) {
        this.listener = listener
    }

    fun show() {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.activity_main_custompopup)
        dlg.setCancelable(false)

        val btnClose = dlg.findViewById<TextView>(R.id.call_bt)
        btnClose.setOnClickListener {
            listener.onOKClicked()
            val phoneNumber = "01032638923" // 전화할 번호를 여기에 설정
            val permission = Manifest.permission.CALL_PHONE

            if (ContextCompat.checkSelfPermission(ctx, permission) == PackageManager.PERMISSION_GRANTED) {
                // 권한이 이미 허용되어 있으면 전화 걸기
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:$phoneNumber")
                ctx.startActivity(intent)
            } else {
                // 권한이 허용되어 있지 않으면 권한 요청
                ActivityCompat.requestPermissions(ctx as Activity, arrayOf(permission), 1)
            }

            dlg.dismiss()
        }

        val btnLink = dlg.findViewById<TextView>(R.id.bt_cancel)
        btnLink.setOnClickListener {
            listener.onOKClicked("신고를 취소합니다")
            dlg.dismiss()
        }
        dlg.show()
    }
}

