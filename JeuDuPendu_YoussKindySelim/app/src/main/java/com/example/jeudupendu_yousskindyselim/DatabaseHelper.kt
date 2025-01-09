package com.example.jeudupendu_yousskindyselim

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,DATABASE_VERSION) {
    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "JeuPendu.db"

        const val HISTORIQUE_TABLE_NAME = "historique"
        const val HISTORIQUE_COLUNM_ID = "id"
        const val HISTORIQUE_COLUNM_SCORE = "score"
        const val HISTORIQUE_COLUMN_MOT_JOUE = "motjoue"
        const val HISTORIQUE_COLUMN_NIVEAU = "niveau"
        const val HISTORIQUE_COLUMN_CONDITION = "condition"
        const val HISTORIQUE_COLUMN_TEMPS_DE_JEU = "tempsdejeu"

        const val DICTIONNAIRE_TABLE_NAME = "dictionnaire"
        const val DICTIONNAIRE_COLUNM_ID = "id"
        const val DICTIONNAIRE_COLUNM_MOT = "mot"
        const val DICTIONNAIRE_COLUNM_LANGUE = "langue"
        const val DICTIONNAIRE_COLUNM_NIVEAU = "niveaudedifficulté"

        const val PREFERENCE_TABLE_NAME = "preference"
        const val PREFERENCE_COLUNM_ID = "id"
        const val PREFERENCE_COLUNM_LANGUE = "langue"
        const val PREFERENCE_COLUNM_NIVEAU = "niveau"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE1 = "CREATE TABLE $HISTORIQUE_TABLE_NAME ($HISTORIQUE_COLUNM_ID INTEGER PRIMARY KEY AUTOINCREMENT,$HISTORIQUE_COLUNM_SCORE,$HISTORIQUE_COLUMN_MOT_JOUE,$HISTORIQUE_COLUMN_NIVEAU,$HISTORIQUE_COLUMN_CONDITION,$HISTORIQUE_COLUMN_TEMPS_DE_JEU)"
        db?.execSQL(CREATE_TABLE1)
        val CREATE_TABLE2 = "CREATE TABLE $DICTIONNAIRE_TABLE_NAME ($DICTIONNAIRE_COLUNM_ID INTEGER PRIMARY KEY AUTOINCREMENT,$DICTIONNAIRE_COLUNM_MOT,$DICTIONNAIRE_COLUNM_LANGUE,$DICTIONNAIRE_COLUNM_NIVEAU)"
        db?.execSQL(CREATE_TABLE2)
        val CREATE_TABLE3 = "CREATE TABLE $PREFERENCE_TABLE_NAME ($PREFERENCE_COLUNM_ID INTEGER PRIMARY KEY AUTOINCREMENT,$PREFERENCE_COLUNM_LANGUE,$PREFERENCE_COLUNM_NIVEAU)"
        db?.execSQL(CREATE_TABLE3)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE1 = "DROP TABLE IF EXISTS $HISTORIQUE_TABLE_NAME"
        db?.execSQL(DROP_TABLE1)
        val DROP_TABLE2 = "DROP TABLE IF EXISTS $DICTIONNAIRE_TABLE_NAME"
        db?.execSQL(DROP_TABLE2)
        val DROP_TABLE3 = "DROP TABLE IF EXISTS $PREFERENCE_TABLE_NAME"
        db?.execSQL(DROP_TABLE3)
        onCreate(db)
    }
}