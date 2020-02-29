package com.dfrobot.angelo.blunobasicdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BlunoLibrary() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onCreateProcess()

        serialBegin(115200)
        send_button.setOnClickListener { serialSend(send_data_edit_text.text.toString()) }
        scan_button.setOnClickListener { buttonScanOnClickProcess() }
    }

    override fun onResume() {
        super.onResume()
        onResumeProcess()
    }

    override fun onPause() {
        super.onPause()
        onPauseProcess()
    }

    override fun onStop() {
        super.onStop()
        onStopProcess()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroyProcess()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        onActivityResultProcess(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onConectionStateChange(connectionState: connectionStateEnum) {
        when (connectionState) {
            connectionStateEnum.isConnected -> scan_button.text = getString(R.string.state_connected)
            connectionStateEnum.isConnecting -> scan_button.text = getString(R.string.state_connecting)
            connectionStateEnum.isToScan -> scan_button.text = getString(R.string.state_scan)
            connectionStateEnum.isScanning -> scan_button.text = getString(R.string.state_scanning)
            connectionStateEnum.isDisconnecting -> scan_button.text = getString(R.string.state_disconnecting)
            connectionStateEnum.isNull -> getString(R.string.state_error)
        }
    }

    override fun onSerialReceived(dataReceived: String) {
        received_data_text_view.append(dataReceived)
        (received_data_text_view.parent as ScrollView).fullScroll(View.FOCUS_DOWN)
    }
}
