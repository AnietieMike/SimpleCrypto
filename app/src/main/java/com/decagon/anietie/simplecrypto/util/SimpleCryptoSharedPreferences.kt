package com.decagon.anietie.simplecrypto.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class SimpleCryptoSharedPreferences constructor(context: Context) {

    private var sharedPreferences: SharedPreferences =
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)

    fun setString(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value)
            apply()
        }
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
            apply()
        }
    }

    fun setInt(key: String, value: Int) {
        sharedPreferences.edit {
            putInt(key, value)
            apply()
        }
    }

    fun getString(key: String, defaultString: String? = null) =
        sharedPreferences.getString(key, defaultString) ?: ""

    fun getBoolean(key: String, defaultBoolean: Boolean = false) =
        sharedPreferences.getBoolean(key, defaultBoolean)

    fun getInt(key: String, defaultInt: Int = 0) = sharedPreferences.getInt(key, defaultInt)
}
