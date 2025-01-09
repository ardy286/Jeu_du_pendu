package com.example.jeudupendu_yousskindyselim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var startJeuBtn: Button
    lateinit var optionJeuBtn: Button
    lateinit var historyBtn: Button
    lateinit var dictionaryBtn: Button
    lateinit var dictionnaireDao : DictionnaireDAO
    lateinit var preferencedao : PreferenceDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val helper = DatabaseHelper(this)
        preferencedao = PreferenceDAO(helper)
        if (preferencedao.getPreferenceById(1) == null){
            preferencedao.insertPreference(Preference("Francais","Facile"))
        }
        startJeuBtn = findViewById(R.id.startJeuBtn)
        optionJeuBtn = findViewById(R.id.optionJeuBtn)
        historyBtn = findViewById(R.id.historyBtn)
        dictionaryBtn = findViewById(R.id.dictionaryBtn)
        dictionnaireDao = DictionnaireDAO(helper)
        setInfoDictionnaire()


        startJeuBtn.setOnClickListener {
            var intention : Intent = Intent(this,MainActivity2Jeux::class.java)

            startActivity(intention)
        }

        historyBtn.setOnClickListener {
            var intention : Intent = Intent(this,MainActivityHistorique::class.java)

            startActivity(intention)
        }

        dictionaryBtn.setOnClickListener {
            var intention : Intent = Intent(this,MainActivityDictionnaire::class.java)

            startActivity(intention)
        }

        optionJeuBtn.setOnClickListener {
            var intention : Intent = Intent(this,MainActivityPreference::class.java)

            startActivity(intention)
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    private fun setInfoDictionnaire(){
        dictionnaireDao.insertMot(Dictionnaire("Manger","Francais","Difficile"))
        dictionnaireDao.insertMot(Dictionnaire("Eat","Anglais","Difficile"))
        dictionnaireDao.insertMot(Dictionnaire("Plage","Francais","Difficile"))
        dictionnaireDao.insertMot(Dictionnaire("Beach","Anglais","Difficile"))
        dictionnaireDao.insertMot(Dictionnaire("Bruller","Francais","Facile"))
        dictionnaireDao.insertMot(Dictionnaire("Burn","Anglais","Facile"))
        dictionnaireDao.insertMot(Dictionnaire("Monstre","Francais","Facile"))
        dictionnaireDao.insertMot(Dictionnaire("Monster","Anglais","Facile"))
        dictionnaireDao.insertMot(Dictionnaire("Violer","Francais","Moyen"))
        dictionnaireDao.insertMot(Dictionnaire("Purple","Anglais","Moyen"))
        dictionnaireDao.insertMot(Dictionnaire("Jaune","Francais","Moyen"))
        dictionnaireDao.insertMot(Dictionnaire("Yellow","Anglais","Moyen"))
    }
}