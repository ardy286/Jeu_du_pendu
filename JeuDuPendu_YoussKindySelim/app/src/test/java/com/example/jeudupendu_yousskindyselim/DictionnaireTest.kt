package com.example.jeudupendu_yousskindyselim

import android.content.Context
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class DictionnaireTest {

    private val context: Context = Mockito.mock(Context::class.java)
    private val dbHelper: DatabaseHelper = DatabaseHelper(context)
    private val dictionnaireDAO: DictionnaireDAO = DictionnaireDAO(dbHelper)



    @Test
    fun ajouterUnMot() {
        val dictionnaire = Dictionnaire("Bonjour", "Francais", "Facile")
        dictionnaireDAO.insertMot(dictionnaire)

        val mots = dictionnaireDAO.getMotsByLangue("Francais")
        assertEquals("Bonjour", mots[0].mot)

    }
}
