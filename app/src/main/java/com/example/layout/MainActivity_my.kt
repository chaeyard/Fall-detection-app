package com.example.layout

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.layout.R.id.bt_cancel
import com.example.layout.R.id.call_bt
import java.io.IOException


import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.DialogInterface
import android.os.Handler
import java.util.UUID

class MainActivity_my : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_my)
        settingButtonok()
        settingButtonok1()
        settingButtonok2()
        val dlg = CustomPopupDialog(this)
        dlg.setOnOKClickedListener(object : CustomPopupDialog.OnClickedListener {
            override fun onOKClicked(msg: String) {
                Toast.makeText(applicationContext, "$msg", Toast.LENGTH_SHORT).show()
                val cancle_n = findViewById<TextView>(R.id.cancel_emergency_num)
                val detect = findViewById<TextView>(R.id.detect_fall_num)
                var number = 0;

                number++
                cancle_n.setText(number.toString())
                detect.setText((number+number_2).toString())

            }

            override fun onOKClicked() {
                Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
                val emer = findViewById<TextView>(R.id.call_emergency_num)
                val detect = findViewById<TextView>(R.id.detect_fall_num)
                var number_2 = 0;

                number_2++
                emer.setText(number_2.toString())
                detect.setText((number+number_2).toString())

            }
        })
        dlg.show()
        plus()
        plus2()




        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val bigrectangle_4 = findViewById<View>(R.id.bigrectangle_4)
        bigrectangle_4.setOnClickListener { listPairedDevices() }

    }



    fun settingButtonok(){
        val button = findViewById<View>(R.id.bigrectangle_1)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_userinfo::class.java)
            startActivity(intent)
        }
    }
    fun settingButtonok1(){
        val button = findViewById<View>(R.id.bigrectangle_3)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_log1::class.java)
            startActivity(intent)
        }
    }
    fun settingButtonok2(){
        val button = findViewById<View>(R.id.bigrectangle_2)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity_signup3::class.java)
            startActivity(intent)
        }
    }
    var number = 0;
    fun plus(){
        var cancle_v = findViewById<View>(R.id.butt1)
        val cancle_n = findViewById<TextView>(R.id.cancel_emergency_num)
        val detect = findViewById<TextView>(R.id.detect_fall_num)

        cancle_v.setOnClickListener{
            number++
            cancle_n.setText(number.toString())
            detect.setText((number+number_2).toString())
        }
    }
    var number_2 = 0;
    fun plus2(){
        var cancle_v = findViewById<View>(R.id.butt2)
        val emer = findViewById<TextView>(R.id.call_emergency_num)
        val detect = findViewById<TextView>(R.id.detect_fall_num)
        cancle_v.setOnClickListener{
            number_2++
            emer.setText(number_2.toString())
            detect.setText((number+number_2).toString())
        }
    }







//블루투스 코드
companion object {
    const val BT_REQUEST_ENABLE = 1
    const val BT_MESSAGE_READ = 2
    const val BT_CONNECTING_STATUS = 3
    val BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
}

    // Bluetooth 관련 변수
    private var mBluetoothAdapter: BluetoothAdapter? = null
    private var mListPairedDevices: MutableList<String>? = null
    private var mBluetoothHandler: Handler? = null
    private var mBluetoothDevice: BluetoothDevice? = null
    private var mBluetoothSocket: BluetoothSocket? = null
    private lateinit var mPairedDevices: Set<BluetoothDevice>

    private fun listPairedDevices() {
        if (mBluetoothAdapter!!.isEnabled) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            mPairedDevices = mBluetoothAdapter!!.bondedDevices
            if (mPairedDevices.size > 0) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("장치 선택")
                mListPairedDevices = ArrayList()
                for (device in mPairedDevices) {
                    mListPairedDevices!!.add(device.name)
                }
                val items = mListPairedDevices!!.toTypedArray<CharSequence>()
                builder.setItems(
                    items
                ) { dialog: DialogInterface?, item: Int ->
                    connectSelectedDevice(
                        items[item].toString()
                    )
                }
                val alert = builder.create()
                alert.show()
            } else {
                Toast.makeText(applicationContext, "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(applicationContext, "블루투스가 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun connectSelectedDevice(selectedDeviceName: String) {
        for (bleDevice in mPairedDevices!!) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            if (selectedDeviceName == bleDevice.name) {
                mBluetoothDevice = bleDevice
                break
            }
        }
        try {
            Toast.makeText(applicationContext, "블루투스가 연결되었습니다.", Toast.LENGTH_LONG).show()
            mBluetoothSocket = mBluetoothDevice!!.createRfcommSocketToServiceRecord(BT_UUID)
            mBluetoothSocket!!.connect()
            mBluetoothHandler!!.obtainMessage(BT_CONNECTING_STATUS, 1, -1).sendToTarget()
        } catch (e: IOException) {
            Log.e("Error Reason", e.toString())
        }
    }



}
