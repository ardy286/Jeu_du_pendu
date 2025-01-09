package com.example.jeudupendu_yousskindyselim

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivityDictionnaire : AppCompatActivity() {
    lateinit var dictionnaireListe: ArrayList<Dictionnaire>
    lateinit var recycler : RecyclerView
    lateinit var adapter : RecyclerAdapterDictionnaire
    lateinit var dictionnaireDao : DictionnaireDAO
    lateinit var preferenceDAO: PreferenceDAO
    lateinit var btnAjouter: Button
    lateinit var retourr : Button
    lateinit var langue : Spinner
    lateinit var niveau : Spinner
    val ADD_CODE = 100



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dictionnaire)

        retourr = findViewById(R.id.retourDic)
        recycler = findViewById(R.id.recyclerD)
        langue = findViewById(R.id.filtreLangue)
        niveau = findViewById(R.id.filtreDifficulte)
        dictionnaireListe = ArrayList()
        val helper = DatabaseHelper(this)
        dictionnaireDao = DictionnaireDAO(helper)
        preferenceDAO = PreferenceDAO(helper)
        btnAjouter = findViewById(R.id.ajouterMot)
        dictionnaireListe = dictionnaireDao.getMotsByLangueAndNiveau("Francais","Facile")
        setInfoAdapter()
        btnAjouter.setOnClickListener {
            val intent = Intent(this, MainActivityAjouterMot::class.java)
            startActivityForResult(intent, ADD_CODE)
        }
        retourr.setOnClickListener { finish() }

        langue.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filtre(langue.selectedItem.toString(),niveau.selectedItem.toString())
                setInfoAdapter()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        niveau.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filtre(langue.selectedItem.toString(),niveau.selectedItem.toString())
                setInfoAdapter()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }




    }

    private fun setInfoAdapter(){
        adapter = RecyclerAdapterDictionnaire(dictionnaireListe)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)

        recycler.layoutManager = layoutManager
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = adapter
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            ADD_CODE ->{
                if(resultCode == RESULT_OK){
                    var mott = data?.extras?.getString("motUtA") ?: ""
                    var mottt = data?.extras?.getString("motUtF") ?: ""
                    var diff = data?.extras?.getString("difficulteUt")?: ""
                    var dictionnaire = Dictionnaire(mott, "Anglais", diff)
                    var dictionnairee = Dictionnaire(mottt, "Francais", diff)
                    dictionnaireListe.add(dictionnaire)
                    dictionnaireListe.add(dictionnairee)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    fun filtre(langue: String,niveau:String){
        if ((langue == "Francais" || langue == "French") &&
            (niveau == "Facile" || niveau == "Easy")){
            dictionnaireListe = dictionnaireDao.getMotsByLangueAndNiveau("Francais","Facile")
            Toast.makeText(getApplicationContext(), "Filtre Francais et facile", Toast.LENGTH_LONG).show();

        }else if ((langue == "Francais" || langue == "French") &&
            (niveau == "Moyen" || niveau == "Medium")){
            dictionnaireListe = dictionnaireDao.getMotsByLangueAndNiveau("Francais","Moyen")
            Toast.makeText(getApplicationContext(), "Filtre Francais et moyen", Toast.LENGTH_LONG).show();

        }else if ((langue == "Francais" || langue == "French") &&
            (niveau == "Difficlie" || niveau == "Hard")){
            dictionnaireListe = dictionnaireDao.getMotsByLangueAndNiveau("Francais","Difficile")
            Toast.makeText(getApplicationContext(), "Filtre Francais et difficile", Toast.LENGTH_LONG).show();

        }else if ((langue == "Francais" || langue == "French") &&
            (niveau == "Aucun" || niveau == "None")){
            dictionnaireListe = dictionnaireDao.getMotsByLangue("Francais")
            Toast.makeText(getApplicationContext(), "Filtre langue Francaise", Toast.LENGTH_LONG).show();

        }else if ((langue == "Anglais" || langue == "English") &&
            (niveau == "Facile" || niveau == "Easy")){
            dictionnaireListe = dictionnaireDao.getMotsByLangueAndNiveau("Anglais","Facile")
            Toast.makeText(getApplicationContext(), "Filtre Anglais et Facile", Toast.LENGTH_LONG).show();

        }else if ((langue == "Anglais" || langue == "English") &&
            (niveau == "Moyen" || niveau == "Medium")){
            dictionnaireListe = dictionnaireDao.getMotsByLangueAndNiveau("Anglais","Moyen")
            Toast.makeText(getApplicationContext(), "Filtre Anglais et Moyen", Toast.LENGTH_LONG).show();

        }else if ((langue == "Anglais" || langue == "English") &&
            (niveau == "Difficlie" || niveau == "Hard")){
            dictionnaireListe = dictionnaireDao.getMotsByLangueAndNiveau("Anglais","Difficile")
            Toast.makeText(getApplicationContext(), "Filtre Anglais et Difficlie", Toast.LENGTH_LONG).show();

        }else if ((langue == "Anglais" || langue == "English") &&
            (niveau == "Aucun" || niveau == "None")){
            dictionnaireListe = dictionnaireDao.getMotsByLangue("Anglais")
            Toast.makeText(getApplicationContext(), "Filtre langue Anglaise", Toast.LENGTH_LONG).show();

        }else if ((langue == "Aucun" || langue == "None") &&
            (niveau == "Facile" || niveau == "Easy")){
            dictionnaireListe = dictionnaireDao.getMotsByNiveau("Facile")
            Toast.makeText(getApplicationContext(), "Filtre niveau facile", Toast.LENGTH_LONG).show();

        }else if ((langue == "Aucun" || langue == "None") &&
            (niveau == "Moyen" || niveau == "Medium")){
            dictionnaireListe = dictionnaireDao.getMotsByNiveau("Moyen")
            Toast.makeText(getApplicationContext(), "Filtre niveau moyen", Toast.LENGTH_LONG).show();

        }else if ((langue == "Aucun" || langue == "None") &&
            (niveau == "Difficlie" || niveau == "Hard")){
            dictionnaireListe = dictionnaireDao.getMotsByNiveau("Difficlie")
            Toast.makeText(getApplicationContext(), "Filtre niveau difficile", Toast.LENGTH_LONG).show();

        }else{
            dictionnaireListe = dictionnaireDao.getAllMots()
            Toast.makeText(getApplicationContext(), "Tout les mots", Toast.LENGTH_LONG).show();
        }
    }

}