package com.example.jeudupendu_yousskindyselim

import android.content.ContentValues
import android.database.Cursor

class PreferenceDAO(val helper : DatabaseHelper) {
    fun insertPreference(preference: Preference){
        if (!preferenceExists(preference)) {
            val db = helper.writableDatabase
            val values = ContentValues().apply {
                put(DatabaseHelper.PREFERENCE_COLUNM_LANGUE, preference.langue)
                put(DatabaseHelper.PREFERENCE_COLUNM_NIVEAU, preference.niveau)
            }
            db.insert(DatabaseHelper.PREFERENCE_TABLE_NAME, null, values)
            db.close()
        }
    }

    private fun preferenceExists(preference: Preference): Boolean {
        val db = helper.readableDatabase
        val selection = "${DatabaseHelper.PREFERENCE_COLUNM_LANGUE} = ? AND ${DatabaseHelper.PREFERENCE_COLUNM_NIVEAU} = ?"
        val selectionArgs = arrayOf(preference.langue, preference.niveau)
        val cursor: Cursor? = db.query(
            DatabaseHelper.PREFERENCE_TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val exists = (cursor?.count ?: 0) > 0
        cursor?.close()
        return exists
    }

    fun updatePreferenceById(id: Int, preference: Preference) {
        val db = helper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.PREFERENCE_COLUNM_LANGUE, preference.langue)
            put(DatabaseHelper.PREFERENCE_COLUNM_NIVEAU, preference.niveau)
        }
        val selection = "${DatabaseHelper.PREFERENCE_COLUNM_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        db.update(DatabaseHelper.PREFERENCE_TABLE_NAME, values, selection, selectionArgs)
        db.close()
    }

    fun getPreferenceById(id: Int): Preference? {
        val db = helper.readableDatabase
        val selection = "${DatabaseHelper.PREFERENCE_COLUNM_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        val cursor = db.query(
            DatabaseHelper.PREFERENCE_TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var preference: Preference? = null
        if (cursor.moveToFirst()) {
            val langue = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PREFERENCE_COLUNM_LANGUE))
            val niveau = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PREFERENCE_COLUNM_NIVEAU))
            preference = Preference(id, langue, niveau)
        }
        cursor.close()
        return preference
    }
}