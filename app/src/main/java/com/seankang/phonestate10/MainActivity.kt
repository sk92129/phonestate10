package com.seankang.phonestate10

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {


    var imei: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {

                getDeviceIMEI()


        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun getDeviceIMEI() : String? {
        var deviceUniqueIdentifier: String? = null
        val tm: TelephonyManager = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        if (null != tm) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_PHONE_STATE),
                        1
                )
            } else {
                try {
                    // already got permission -- so get it.
                    deviceUniqueIdentifier = tm.getDeviceId()
                    imei = tm.getImei(0)

                } catch (e: Throwable){
                    e.printStackTrace()
                }
            }
            if (null == deviceUniqueIdentifier || 0 == deviceUniqueIdentifier.length) deviceUniqueIdentifier =
                    "0"
        }
        return deviceUniqueIdentifier
    }
}