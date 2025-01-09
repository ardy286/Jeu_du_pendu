package com.example.jeudupendu_yousskindyselim

class Dictionnaire
{
    var id: Int = 0
    var mot: String = ""
    var langue: String = ""
    var niveauDeDifficulte: String = ""

    constructor(mot: String, langue: String, niveauDeDifficulte: String) {
        this.mot = mot
        this.langue = langue
        this.niveauDeDifficulte = niveauDeDifficulte
    }

    constructor(id:Int,mot: String, langue: String, niveauDeDifficulte: String) {
        this.id = id
        this.mot = mot
        this.langue = langue
        this.niveauDeDifficulte = niveauDeDifficulte
    }


}