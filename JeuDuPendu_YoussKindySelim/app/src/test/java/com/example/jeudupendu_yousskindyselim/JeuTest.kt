package com.example.jeudupendu_yousskindyselim

import Jeu
import org.junit.Assert.*
import org.junit.Test

class JeuTest{
    val list1 = arrayListOf(
        Dictionnaire( "Bonjour", "Francais", "Facile"),
    )
    var list2 = arrayListOf<Dictionnaire>()


    var jeu: Jeu = Jeu(listOf("Bonjour"))

    @Test
    fun constructeurSansArgument() {
        println("constructeur avec liste vide")
        val lst = emptyList<String>()
        assertThrows(IllegalArgumentException::class.java) {
            Jeu(lst)
        }

    }

    @Test
    fun constructeurAvecArguments() {
        println("contructeur avec liste non vide")
        var jeu:Jeu = Jeu(listOf("Bonjour"))
        var expResult = "Bonjour"
        var result = jeu.motADeviner
        assertEquals(expResult, result)
        println(expResult)

    }
}