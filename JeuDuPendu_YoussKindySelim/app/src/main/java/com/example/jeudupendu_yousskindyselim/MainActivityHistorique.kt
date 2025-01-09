package com.example.jeudupendu_yousskindyselim

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivityHistorique : AppCompatActivity() {
    lateinit var historiqueList: ArrayList<Historique>
    lateinit var recycler : RecyclerView
    lateinit var adapter : RecyclerAdapterHistorique
    lateinit var retour : Button
    lateinit var historiqueDAO: HistoriqueDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_historique)
        val helper = DatabaseHelper(this)
        historiqueDAO = HistoriqueDAO(helper)
        historiqueList = historiqueDAO.getAllHistoriques()
        retour = findViewById(R.id.retourHis)
        recycler = findViewById(R.id.recycler)
        setInfoAdapter()
        retour.setOnClickListener{
            finish()
        }
    }



    private fun setInfoAdapter(){
        adapter = RecyclerAdapterHistorique(historiqueList)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)

        recycler.layoutManager = layoutManager
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = adapter
    }
}