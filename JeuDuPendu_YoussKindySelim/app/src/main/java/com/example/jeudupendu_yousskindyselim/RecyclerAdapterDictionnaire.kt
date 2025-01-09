package com.example.jeudupendu_yousskindyselim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterDictionnaire(var listeDictionnaire:ArrayList<Dictionnaire>):
RecyclerView.Adapter<RecyclerAdapterDictionnaire.MyViewHolder>(){
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        lateinit var btnSupMot1: ImageView

        var mot : TextView
        var langue : TextView
        var difficulte: TextView


        init {

            mot = itemView.findViewById(R.id.mot)
            langue = itemView.findViewById(R.id.langue)
            difficulte = itemView.findViewById(R.id.difficulte)
            btnSupMot1 = itemView.findViewById(R.id.btnSupMot1)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var dictionnaireView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_dictionnaire,parent,false)

        return MyViewHolder(dictionnaireView)
    }

    override fun getItemCount(): Int {
        return listeDictionnaire.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val helper = DatabaseHelper(holder.itemView.context)
        val dictionnaireDAO= DictionnaireDAO(helper)
        var mot : String = listeDictionnaire[position].mot
        var langue : String = listeDictionnaire[position].langue
        var difficulte : String = listeDictionnaire[position].niveauDeDifficulte


        holder.mot.text = "Mot : " + mot
        holder.langue.text = "Langue : " + langue
        holder.difficulte.text = "Difficulte : " + difficulte






        holder.btnSupMot1.setOnClickListener {
            val motASupprimer = listeDictionnaire[position]
            val motPrecedent = if (position > 0) listeDictionnaire[position - 1] else null
            val motSuivant = if (position < listeDictionnaire.size - 1) listeDictionnaire[position + 1] else null

            // verif si le mot supprime est en français
            if (motASupprimer.langue == "Francais") {
                // Supprimer le mot en français
                dictionnaireDAO.deleteMot(motASupprimer.id)

                listeDictionnaire.removeAt(position)

                // verif et supp le mot juste après
                if (motSuivant != null && motSuivant.langue == "Anglais") {
                    dictionnaireDAO.deleteMot(motSuivant.id)
                    listeDictionnaire.removeAt(position) // Suppre du mot suivant
                    notifyDataSetChanged()
                }
            } else if (motASupprimer.langue == "Anglais") {
                // supp le mot en anglais
                dictionnaireDAO.deleteMot(motASupprimer.id)
                listeDictionnaire.removeAt(position)

                // vérifie et supp le mot juste avant
                if (motPrecedent != null && motPrecedent.langue == "Francais") {
                    dictionnaireDAO.deleteMot(motPrecedent.id)
                    listeDictionnaire.removeAt(position - 1) // Suppr du mot précédent
                    notifyDataSetChanged()
                }
            }
        }

    }



}