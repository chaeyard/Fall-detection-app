package com.example.layout

import android.Manifest
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.util.UUID

class sua_1 : AppCompatActivity() {

    // Bluetooth 상수
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_my)

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val bigrectangle_4 = findViewById<View>(R.id.bigrectangle_4)
        bigrectangle_4.setOnClickListener { listPairedDevices() }
    }

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
