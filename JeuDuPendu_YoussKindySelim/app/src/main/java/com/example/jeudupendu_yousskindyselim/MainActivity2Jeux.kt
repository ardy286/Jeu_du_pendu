package com.example.jeudupendu_yousskindyselim

import Jeu
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Timer
import java.util.TimerTask
import kotlin.random.Random

class MainActivity2Jeux : AppCompatActivity() {
    private lateinit var jeu: Jeu
    private lateinit var wordDisplay: TextView
    private lateinit var scoreText: TextView
    private lateinit var hangmanImage: ImageView
    private val guessedLetters = mutableSetOf<Char>()
    private lateinit var temps : TextView
    private lateinit var timer: Timer
    private lateinit var retourneee: Button
    private lateinit var recommencer: Button
    private var secondsElapsed: Int = 0
    lateinit var preferencedao : PreferenceDAO
    lateinit var dictionnaireDAO: DictionnaireDAO
    lateinit var preference: Preference
    lateinit var mots: ArrayList<Dictionnaire>
    lateinit var historiqueDAO: HistoriqueDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_activity2_jeux)
        val helper = DatabaseHelper(this)
        preferencedao = PreferenceDAO(helper)
        preference = preferencedao.getPreferenceById(1)!!
        dictionnaireDAO = DictionnaireDAO(helper)
        historiqueDAO = HistoriqueDAO(helper)
        mots = dictionnaireDAO.getMotsByLangueAndNiveau(preference.langue,preference.niveau)
        temps = findViewById(R.id.temps)
        retourneee = findViewById(R.id.retourJeu)
        recommencer = findViewById(R.id.recommencer)
        retourneee.setOnClickListener { finish() }
        recommencer.setOnClickListener {
            var intention : Intent = Intent(this,MainActivity2Jeux::class.java)

            startActivity(intention)
            finish()
        }

        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                secondsElapsed++
                val hours = secondsElapsed / 3600
                val minutes = (secondsElapsed % 3600) / 60
                val seconds = secondsElapsed % 60

                val formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds)

                // Mettre à jour l'interface utilisateur depuis le thread principal
                runOnUiThread {
                    temps.text = formattedTime
                }
            }
        }, 0, 1000)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //liste des mots
        val entierAleatoire = Random.nextInt(0,mots.size)
        val words =listOf(mots[entierAleatoire].mot)
        jeu = Jeu(words)

        // trouver les composants
        wordDisplay =findViewById(R.id.word_display)
        scoreText = findViewById(R.id.score)
        hangmanImage = findViewById(R.id.img)
        val alphabet_buttons: GridLayout = findViewById(R.id.alphabet_buttons)

        // Initialiser UI
        updateLaffichageMot()
        updateScore()

        //mettre le onClickListener sur tt les lettres

        for (i in 0 until alphabet_buttons.childCount) {
            val button = alphabet_buttons.getChildAt(i) as Button
            button.setOnClickListener {
                val letter = button.text[0]
                gererLaffichageDesLettre(letter, button)
            }
        }

    }

    //fun pour verifier si la lettre entree est la bonne lettre

    private fun gererLaffichageDesLettre(letter: Char, button: Button) {
        val indices = jeu.essayerUneLettre(letter.lowercaseChar())

        if (indices.isEmpty()) {
            //on update l'image chaque erreur
            updateImage(jeu.nbErreurs)
        } else {
            //sinon on affiche la lettre
            guessedLetters.add(letter.lowercaseChar())
            updateLaffichageMot()
        }
        //le bouton marche plus

        button.isEnabled = false
        button.setBackgroundColor(Color.RED);



        updateScore()
        //si on arrive a trouver le mots on affiche le dialogue de reussite
        if (jeu.estRéussi()) {
            var historique: Historique = Historique(scoreText.text.toString(),jeu.motADeviner,preference.niveau,"Gagné",temps.text.toString())
            historiqueDAO.insertHistorique(historique)
            showVictoryDialog()
        }
    }

    //on update le mot a deviner qd on ajoute plusiers ots dans la liste

    private fun updateLaffichageMot() {
        val displayedWord = StringBuilder()
        for (i in jeu.motADeviner.indices) {
            val char = jeu.motADeviner[i].lowercaseChar()
            displayedWord.append(if (char in guessedLetters) char else " _ ")
        }
        wordDisplay.text = "Trouver ce Mot: $displayedWord"
    }
//l'affichage du score
    private fun updateScore() {
        scoreText.text = "${jeu.pointage}"
    }

    //changer l'image a chaque erreur en utilisant le nb d'erreur

    private fun updateImage(nbErreurs: Int) {
        val imageResId = when (nbErreurs) {
            1 -> R.drawable.err01
            2 -> R.drawable.err02
            3 -> R.drawable.err03
            4 -> R.drawable.err04
            5 -> R.drawable.err05
            6 -> R.drawable.err06
            else -> R.drawable.acceuil
        }
        hangmanImage.setImageResource(imageResId)
        if (nbErreurs == 6) {
            var historique: Historique = Historique(scoreText.text.toString(),jeu.motADeviner,preference.niveau,"Perdu",temps.text.toString())
            historiqueDAO.insertHistorique(historique)
            showOverDialog()
        }
    }




    private fun showVictoryDialog() {
        AlertDialog.Builder(this)
            .setTitle("Félicitations!")
            .setMessage("Vous avez gagné le jeu.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                goVersAccueil()
            }
            .show()
    }

    private fun showOverDialog() {
        AlertDialog.Builder(this)
            .setTitle("GameOver!")
            .setMessage("Vous avez depasser le nombre de fautes.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                goVersAccueil()
            }
            .show()
    }

    private fun goVersAccueil(){
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()

    }


}