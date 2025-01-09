package com.example.jeudupendu_yousskindyselim

class Preference {
    var id: Int = 0
    var langue: String = ""
    var niveau: String = ""

    constructor( langue: String, niveau: String) {
        this.langue = langue
        this.niveau = niveau
    }

    constructor(id:Int, langue: String, niveau: String) {
        this.id = id
        this.langue = langue
        this.niveau = niveau
    }
}