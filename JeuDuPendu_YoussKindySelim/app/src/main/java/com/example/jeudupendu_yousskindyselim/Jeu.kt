import java.lang.IllegalArgumentException
import java.util.*

class Jeu(mots: List<String>) {
    val motADeviner: String
    private var points = 0
    private var erreurs = 0

    val pointage: Int get() = points
    val nbErreurs: Int get() = erreurs

    init {
        if (mots.isEmpty()) {
            throw IllegalArgumentException("Il faut un mot dans le dictionnaire pour pouvoir lancer le jeu")
        }else{
        require(mots.isNotEmpty()) { "la liste des mots peut pas etre vide" }
        motADeviner = mots[Random().nextInt(mots.size)]
        }
    }

    fun essayerUneLettre(lettre: Char): List<Int> {
        val lettreLower = lettre.lowercaseChar()
        val indices = motADeviner.indices.filter { motADeviner[it].lowercaseChar() == lettreLower }

        if (indices.isEmpty()) {
            erreurs++
        } else {
            points += indices.size
        }

        return indices
    }

    fun estRéussi(): Boolean {
        return points == motADeviner.length
    }
}
