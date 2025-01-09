package com.example.jeudupendu_yousskindyselim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterHistorique(var historiqueList:ArrayList<Historique>):
    RecyclerView.Adapter<RecyclerAdapterHistorique.MyViewHolder>()
{
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var phrase : TextView

        init {
            phrase = itemView.findViewById(R.id.phrase)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var historiqueView : View = LayoutInflater.from(parent.context).inflate(R.layout.list_historique,parent,false) // c'est ici qu'om met le xml list item pour afficher les produit

        return MyViewHolder(historiqueView)
    }

    override fun getItemCount(): Int {
        return historiqueList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var motJoue : String = historiqueList[position].motJoue
        var niveau : String = historiqueList[position].niveau
        var tempsDeJeu : String = historiqueList[position].tempsDeJeu
        var score : String = historiqueList[position].score
        var condition : String = historiqueList[position].condition
        holder.phrase.text = "Vous avez "+condition+" en "+tempsDeJeu+" avec un score de "+score+
                ". Le mot joué est "+motJoue+", le niveau du mot est "+niveau+"."


    }
}