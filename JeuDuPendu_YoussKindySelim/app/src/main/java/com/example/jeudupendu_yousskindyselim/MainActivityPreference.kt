package com.example.jeudupendu_yousskindyselim

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivityPreference : AppCompatActivity() {
    lateinit var retourner: Button
    private lateinit var preferenceDAO: PreferenceDAO

    private lateinit var preference: Preference

    lateinit var langRadioGroup: RadioGroup
    lateinit var niveauRadioGroup: RadioGroup
    lateinit var radioFr: RadioButton
    lateinit var radioEn: RadioButton
    lateinit var radioFacile: RadioButton
    lateinit var radioMoyen: RadioButton
    lateinit var radioDificile: RadioButton

    lateinit var langue2: String
    lateinit var niveau2: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_preference)
        retourner = findViewById(R.id.retourPrefer)

        preferenceDAO = PreferenceDAO(DatabaseHelper(this))
        preference = preferenceDAO.getPreferenceById(1)!!

        niveau2 = preference.niveau
        langue2 = preference.langue

        retourner.setOnClickListener { finish() }

        radioFr = findViewById(R.id.radioFr)
        radioEn = findViewById(R.id.radioEn)
        radioFacile = findViewById(R.id.radioFacile)
        radioMoyen = findViewById(R.id.radioMoyen)
        radioDificile = findViewById(R.id.radioDificile)

        langRadioGroup = findViewById(R.id.langPrefer)
        niveauRadioGroup = findViewById(R.id.niveauPrefer)

        if (niveau2 == "Facile") {
            niveauRadioGroup.check(radioFacile.id)
        } else if (niveau2 == "Moyen") {
            niveauRadioGroup.check(radioMoyen.id)
        } else {
            niveauRadioGroup.check(radioDificile.id)
        }

        if (langue2 == "Francais") {
            langRadioGroup.check(radioFr.id)
        } else {
            langRadioGroup.check(radioEn.id)
        }

        langRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedLangRadioButton = findViewById<RadioButton>(checkedId)
            var selectedLang = selectedLangRadioButton.text.toString()
            if (selectedLang == "Francais" || selectedLang == "French") {
                selectedLang = "Francais"
            } else {
                selectedLang = "Anglais"
            }
            updatePreference(selectedLang, niveau2)
        }

        niveauRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedNiveauRadioButton = findViewById<RadioButton>(checkedId)
            var selectedNiveau = selectedNiveauRadioButton.text.toString()
            if (selectedNiveau == "Facile" || selectedNiveau == "Easy") {
                selectedNiveau = "Facile"
            } else if (selectedNiveau == "Moyen" || selectedNiveau == "Medium") {
                selectedNiveau = "Moyen"
            } else {
                selectedNiveau = "Difficile"
            }
            updatePreference(langue2, selectedNiveau)
        }
    }

    private fun updatePreference(langue: String, niveau: String) {
        val existingPreference = preferenceDAO.getPreferenceById(1)

        if (existingPreference != null) {
            val updatedPreference = Preference(existingPreference.id, langue, niveau)
            preferenceDAO.updatePreferenceById(existingPreference.id, updatedPreference)
            Toast.makeText(applicationContext, "Préférence modifiée avec succès", Toast.LENGTH_LONG).show()

            // Mettez à jour les variables de niveau2 et langue2
            langue2 = langue
            niveau2 = niveau

        } else {
            val newPreference = Preference(langue, niveau)
            preferenceDAO.insertPreference(newPreference)
        }
    }
}
