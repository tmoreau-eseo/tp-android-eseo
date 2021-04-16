package com.example.tp_eseo_theophilem.Main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tp_eseo_theophilem.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Bouton gérant la localisation
        findViewById<Button>(R.id.bouton_localisation).setOnClickListener {
            //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:47.472822,-0.5621756")));
        }

        // Bouton gérant l'historique des localisations
        findViewById<Button>(R.id.bouton_historique).setOnClickListener {
            // Votre action
        }

        // Bouton permettant l'accès aux paramères
        findViewById<Button>(R.id.bouton_parametres).setOnClickListener {
            val targetIntent = Intent().apply {
                action = android.provider.Settings.ACTION_SETTINGS;
            }
        }
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}