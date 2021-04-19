package com.example.tp_eseo_theophilem.ui.Historique

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_eseo_theophilem.R
import com.example.tp_eseo_theophilem.ux.HistoriqueAdapter
import com.example.tp_eseo_theophilem.ux.LocalPreferences


class HistoriqueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historique_rv)

        val locationList: ArrayList<String> = ArrayList()
        LocalPreferences.getInstance(this).getHistory()?.iterator()?.forEach {
            locationList.add(it)
        };


        val recyclerView = findViewById<RecyclerView>(R.id.item_list)
        recyclerView.adapter = HistoriqueAdapter(locationList.toTypedArray())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

    }
}