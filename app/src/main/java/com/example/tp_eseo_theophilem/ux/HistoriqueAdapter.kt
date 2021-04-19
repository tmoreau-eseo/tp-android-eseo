package com.example.tp_eseo_theophilem.ui.Historique

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_eseo_theophilem.R

class HistoriqueAdapter(private val locList: Array<String>) : RecyclerView.Adapter<HistoriqueAdapter.ViewHolder>() {
    fun onCreate() {}

    // Comment s'affiche ma vue
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            textView = itemView.findViewById<TextView>(R.id.item_list_text)
        }
    }

    // Retourne une « vue » / « layout » pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_loc, parent, false)
        return ViewHolder(view)
    }

    // Connect la vue ET la données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = locList[position]
    }

    override fun getItemCount(): Int {
        return locList.size
    }


}