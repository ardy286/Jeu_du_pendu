package com.example.jeudupendu_yousskindyselim

import android.content.ContentValues
import android.database.Cursor

class HistoriqueDAO(val helper: DatabaseHelper) {
    fun insertHistorique(historique: Historique){
        val db = helper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.HISTORIQUE_COLUNM_SCORE,historique.score)
            put(DatabaseHelper.HISTORIQUE_COLUMN_MOT_JOUE,historique.motJoue)
            put(DatabaseHelper.HISTORIQUE_COLUMN_NIVEAU,historique.niveau)
            put(DatabaseHelper.HISTORIQUE_COLUMN_CONDITION,historique.condition)
            put(DatabaseHelper.HISTORIQUE_COLUMN_TEMPS_DE_JEU,historique.tempsDeJeu)
        }
        db.insert(DatabaseHelper.HISTORIQUE_TABLE_NAME,null,values)
        db.close()
    }

    fun deleteHistorique(id: Int){
        val db = helper.writableDatabase
        db.delete(DatabaseHelper.HISTORIQUE_TABLE_NAME,"${DatabaseHelper.HISTORIQUE_COLUNM_ID} = ?", arrayOf(id.toString()))
    }

    fun getAllHistoriques(): ArrayList<Historique> {
        val historiquesList = ArrayList<Historique>()
        val db = helper.readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM ${DatabaseHelper.HISTORIQUE_TABLE_NAME}", null)
        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndexOrThrow(DatabaseHelper.HISTORIQUE_COLUNM_ID))
                    val score = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.HISTORIQUE_COLUNM_SCORE))
                    val motJoue = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.HISTORIQUE_COLUMN_MOT_JOUE))
                    val niveau = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.HISTORIQUE_COLUMN_NIVEAU))
                    val condition = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.HISTORIQUE_COLUMN_CONDITION))
                    val tempsDeJeu = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.HISTORIQUE_COLUMN_TEMPS_DE_JEU))
                    val historique = Historique(id, score,motJoue, niveau, condition , tempsDeJeu)
                    historiquesList.add(historique)
                } while (it.moveToNext())
            }
        }
        cursor?.close()
        db.close()
        return historiquesList
    }

}