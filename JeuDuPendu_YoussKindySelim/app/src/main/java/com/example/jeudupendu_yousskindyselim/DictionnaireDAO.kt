package com.example.jeudupendu_yousskindyselim

import android.content.ContentValues
import android.database.Cursor

class DictionnaireDAO(val helper: DatabaseHelper) {

    fun insertMot(dictionnaire: Dictionnaire){
        if (!motExisteDeja(dictionnaire.mot)) { // Vérifie si le mot n'existe pas déjà
            val db = helper.writableDatabase
            val values = ContentValues().apply {
                put(DatabaseHelper.DICTIONNAIRE_COLUNM_MOT,dictionnaire.mot)
                put(DatabaseHelper.DICTIONNAIRE_COLUNM_LANGUE,dictionnaire.langue)
                put(DatabaseHelper.DICTIONNAIRE_COLUNM_NIVEAU,dictionnaire.niveauDeDifficulte)
            }
            db.insert(DatabaseHelper.DICTIONNAIRE_TABLE_NAME,null,values)
            db.close()
        }
    }


    fun motExisteDeja(mot: String): Boolean {
        val db = helper.readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT COUNT(*) FROM ${DatabaseHelper.DICTIONNAIRE_TABLE_NAME} WHERE ${DatabaseHelper.DICTIONNAIRE_COLUNM_MOT} = ?", arrayOf(mot))
        var existe = false
        cursor?.use {
            if (it.moveToFirst()) {
                val count = it.getInt(0)
                existe = count > 0
            }
        }
        cursor?.close()
        db.close()
        return existe
    }


    fun deleteMot(id: Int) {
        val db = helper.writableDatabase
        db.delete(DatabaseHelper.DICTIONNAIRE_TABLE_NAME, "${DatabaseHelper.DICTIONNAIRE_COLUNM_ID} = ?", arrayOf(id.toString()))
        db.close()
    }

    fun deleteAllMots() {
        val db = helper.writableDatabase
        db.delete(DatabaseHelper.DICTIONNAIRE_TABLE_NAME, null, null)
        db.close()
    }

    fun getMotsByLangue(langue: String): ArrayList<Dictionnaire> {
        val listMot = ArrayList<Dictionnaire>()
        val db = helper.readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM ${DatabaseHelper.DICTIONNAIRE_TABLE_NAME} WHERE ${DatabaseHelper.DICTIONNAIRE_COLUNM_LANGUE} = ?", arrayOf(langue))
        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_ID))
                    val mot = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_MOT))
                    val langue = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_LANGUE))
                    val niveau = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_NIVEAU))
                    val dictionnaire = Dictionnaire(id, mot, langue, niveau)
                    listMot.add(dictionnaire)
                } while (it.moveToNext())
            }
        }
        cursor?.close()
        db.close()
        return listMot
    }

    fun getMotsByNiveau(niveauDeDifficulte: String): ArrayList<Dictionnaire> {
        val listMot = ArrayList<Dictionnaire>()
        val db = helper.readableDatabase
        val selection = "${DatabaseHelper.DICTIONNAIRE_COLUNM_NIVEAU} = ?"
        val selectionArgs = arrayOf(niveauDeDifficulte)
        val cursor: Cursor? = db.query(
            DatabaseHelper.DICTIONNAIRE_TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_ID))
                    val mot = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_MOT))
                    val langue = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_LANGUE))
                    val niveau = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_NIVEAU))
                    val dictionnaire = Dictionnaire(id, mot, langue, niveau)
                    listMot.add(dictionnaire)
                } while (it.moveToNext())
            }
        }
        cursor?.close()
        db.close()
        return listMot
    }



    fun getAllMots(): ArrayList<Dictionnaire> {
        val listMot = ArrayList<Dictionnaire>()
        val db = helper.readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM ${DatabaseHelper.DICTIONNAIRE_TABLE_NAME}", null)
        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_ID))
                    val mot = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_MOT))
                    val langue = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_LANGUE))
                    val niveau = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_NIVEAU))
                    val dictionnaire = Dictionnaire(id, mot, langue, niveau)
                    listMot.add(dictionnaire)
                } while (it.moveToNext())
            }
        }
        cursor?.close()
        db.close()
        return listMot
    }

    fun getMotsByLangueAndNiveau(langue: String, niveauDeDifficulte: String): ArrayList<Dictionnaire> {
        val listMot = ArrayList<Dictionnaire>()
        val db = helper.readableDatabase
        val selection = "${DatabaseHelper.DICTIONNAIRE_COLUNM_LANGUE} = ? AND ${DatabaseHelper.DICTIONNAIRE_COLUNM_NIVEAU} = ?"
        val selectionArgs = arrayOf(langue, niveauDeDifficulte)
        val cursor: Cursor? = db.query(
            DatabaseHelper.DICTIONNAIRE_TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_ID))
                    val mot = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_MOT))
                    val langue = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_LANGUE))
                    val niveau = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.DICTIONNAIRE_COLUNM_NIVEAU))
                    val dictionnaire = Dictionnaire(id, mot, langue, niveau)
                    listMot.add(dictionnaire)
                } while (it.moveToNext())
            }
        }
        cursor?.close()
        db.close()
        return listMot
    }

}