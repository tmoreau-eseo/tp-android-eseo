package com.example.tp_eseo_theophilem.Main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tp_eseo_theophilem.ui.Map.MapsActivity
import com.example.tp_eseo_theophilem.R
import com.example.tp_eseo_theophilem.SettingsActivity
import com.example.tp_eseo_theophilem.ux.HistoriqueAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Bouton gérant la localisation
        findViewById<Button>(R.id.bouton_localisation).setOnClickListener {
            //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:47.49308172693892, -0.5513494662200698")));
            startActivity(Intent(this, MapsActivity::class.java));
        }

        // Bouton gérant l'historique des localisations
        findViewById<Button>(R.id.bouton_historique).setOnClickListener {

            startActivity(Intent(this, HistoriqueAdapter::class.java));
        }

        // Bouton permettant l'accès aux paramètres des applications
        findViewById<Button>(R.id.bouton_parametres).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java));
        }
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}