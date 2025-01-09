package com.example.jeudupendu_yousskindyselim

class Historique
{
    var id: Int = 0
    var score: String = ""
    var motJoue: String = ""
    var niveau: String = ""
    var condition: String = ""
    var tempsDeJeu: String = ""

    constructor(score: String, motJoue: String, niveau: String, condition: String, tempsDeJeu: String) {
        this.score = score
        this.motJoue = motJoue
        this.niveau = niveau
        this.condition = condition
        this.tempsDeJeu = tempsDeJeu
    }

    constructor(id: Int, score: String,motJoue: String, niveau: String, condition: String, tempsDeJeu: String) {
        this.id = id
        this.score = score
        this.motJoue = motJoue
        this.niveau = niveau
        this.condition = condition
        this.tempsDeJeu = tempsDeJeu
    }

}