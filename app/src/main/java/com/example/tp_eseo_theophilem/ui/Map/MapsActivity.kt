package com.example.tp_eseo_theophilem.ui.Map

import com.example.tp_eseo_theophilem.ux.LocalPreferences
import kotlin.String
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.tp_eseo_theophilem.Main.MainActivity
import com.example.tp_eseo_theophilem.R
import java.util.*
import kotlin.math.round

class MapsActivity : AppCompatActivity() {
    companion object {
        const val PERMISSION_REQUEST_LOCATION = 9999

        fun getStartIntent(context: Context): Intent {
            return Intent(context, MapsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        supportActionBar?.apply {
            setTitle(R.string.localisation)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        try{
            requestPermission();

        }catch (e: Exception){
            val toast = Toast.makeText(applicationContext, R.string.permitionNotAcepted, LENGTH_LONG)
            toast.show()
            startActivity(Intent(this, MainActivity::class.java));
        }

//        val location = getLocation();
//        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:47.472822,-0.5621756")));
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission obtenue, Nous continuons la suite de la logique.
                    getLocation()
                } else {
                    val toast = Toast.makeText(applicationContext, R.string.permitionNotAcepted, LENGTH_LONG)
                    toast.show()
                    startActivity(Intent(this, MainActivity::class.java));
                    // TODO
                    // Permission non accepté, expliqué ici via une activité ou une dialog pourquoi nous avons besoin de la permission
                }
                return
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (hasPermission()) {
            val locationManager = applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager?
            locationManager?.run {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000,
                    10000F,
                    LocationListener {
                        geoCode(
                            it)
                    });
            }
        }
    }

    private fun requestPermission() {
        if (!hasPermission()) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_LOCATION)
        } else {
            getLocation()
        }
    }

    private fun hasPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("SetTextI18n")
    private fun geoCode(location: Location){
        val geocoder = Geocoder(this, Locale.getDefault())
        val results = geocoder.getFromLocation(location.latitude, location.longitude, 1)

        val locationText = findViewById<TextView>(R.id.locationText)
        val locationToEseo = findViewById<TextView>(R.id.locationTextToEseo)
        val locationToEseoKm = findViewById<TextView>(R.id.locationTextToEseoKm)

        if (results.isNotEmpty()) {
            locationText.text = results[0].getAddressLine(0);
            val currentLocation = results[0].getAddressLine(0);
            var eseoLat = 47.49369227314271;
            var eseoLong = -0.5512572285386245;
            val locationEseo = Location("ESEO");
            locationEseo.latitude = eseoLat;
            locationEseo.longitude = eseoLong;
            var distance = location.distanceTo(locationEseo);

            val distanceMetre = round(distance)
            val lTET1 = R.string.messageDistance1;
            val lTET2 = distanceMetre.toString();
            val lTET3 = R.string.messageDistance2;
            val locationToEseoText = lTET1.toString() + lTET2 + lTET3.toString()
            locationToEseo.text = locationToEseoText;
            val distanceKm = distanceMetre / 1000;
            val tkm1 = R.string.messageKm1;
            val tkm2 = R.string.messageKm2;
            locationToEseoKm.text = tkm1.toString() + distanceKm.toString() + tkm2.toString();
            //val date = Calendar.getInstance().time;
            LocalPreferences.getInstance(this).addToHistory(currentLocation);
        }else
        {
            val toast = Toast.makeText(applicationContext, R.string.permitionNotAcepted, LENGTH_LONG)
            toast.show()
        }
    }


}


