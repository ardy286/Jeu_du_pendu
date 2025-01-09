package com.example.jeudupendu_yousskindyselim

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivityAjouterMot : AppCompatActivity() {
    lateinit var motA: EditText
    lateinit var motF: EditText
    lateinit var radioGroupDifficulte: RadioGroup
    lateinit var btnAjouter: Button
    lateinit var dictionnaireDAO: DictionnaireDAO
    lateinit var retourrr: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_ajouter_mot)
        motA = findViewById(R.id.motAUt)
        motF = findViewById(R.id.motFUt)
        retourrr = findViewById(R.id.retourAjout)
        val helper = DatabaseHelper(this)
        dictionnaireDAO = DictionnaireDAO(helper)
        radioGroupDifficulte = findViewById(R.id.radioGroupDiff)
        btnAjouter = findViewById(R.id.ajoutMot)

        retourrr.setOnClickListener { finish() }

        btnAjouter.setOnClickListener {
            val motsTextA = motA.text.toString().trim()
            val motsTextF = motF.text.toString().trim()
            val checkedRadioButtonId = radioGroupDifficulte.checkedRadioButtonId

            if (motsTextA.isNotEmpty() && motsTextF.isNotEmpty() && checkedRadioButtonId != -1) {
                val intention = Intent()
                val langueTextA = "Anglais"
                val langueTextF = "Francais"
                val difficulte = findViewById<RadioButton>(checkedRadioButtonId)
                var difficulteText = difficulte.text.toString()
                if (difficulteText == "Difficile" || difficulteText == "Hard") {
                    difficulteText = "Difficile"
                } else if (difficulteText == "Moyen" || difficulteText == "Medium") {
                    difficulteText = "Moyen"
                } else {
                    difficulteText = "Facile"
                }

                intention.putExtra("motUtA", motsTextA)
                intention.putExtra("motUtF", motsTextF)
                intention.putExtra("difficulteUt", difficulteText)
                setResult(RESULT_OK, intention)

                val nouveauMots = Dictionnaire(motsTextA, langueTextA, difficulteText)
                val nouveauMotss = Dictionnaire(motsTextF, langueTextF, difficulteText)
                dictionnaireDAO.insertMot(nouveauMotss)
                dictionnaireDAO.insertMot(nouveauMots)

                finish()
            } else {
                Toast.makeText(applicationContext, "Remplir tous les champs de saisie et sélectionner une difficulté", Toast.LENGTH_LONG).show()
            }
        }
    }
}
