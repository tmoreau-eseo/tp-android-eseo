package com.example.tp_eseo_theophilem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.apply {
            setTitle(R.string.settings)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

    // Bouton accès aux paramètres
    findViewById<Button>(R.id.bouton_settings).setOnClickListener{
        val targetIntentSettings = Intent().apply {
                action = android.provider.Settings.ACTION_APPLICATION_SETTINGS;
        }
        startActivity(targetIntentSettings);
    }

    // Bouton accès aux paramètres de localisation
    findViewById<Button>(R.id.bouton_settings_location).setOnClickListener{
        val targetIntentSettingsLoc = Intent().apply {
            action = android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS;
        }
        startActivity(targetIntentSettingsLoc);
    }

    // Bouton d'ouverture de la carte
    findViewById<Button>(R.id.bouton_map_eseo).setOnClickListener{
        startActivity(Intent(Intent.ACTION_VIEW,
            Uri.parse("geo:47.49308172693892, -0.5513494662200698")));
    }

    // Bouton d'ouverture du site de l'eseo
    findViewById<Button>(R.id.bouton_site_eseo).setOnClickListener{
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://eseo.fr/")));
    }

        // Bouton d'ouverture des mails
    findViewById<Button>(R.id.bouton_mail).setOnClickListener{
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:theophile.moreau@reseau.eseo.fr")
        startActivity(emailIntent);
    }

    }
}