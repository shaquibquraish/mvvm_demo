package com.shaquibquraishi.kotlindemo.data.sharedPref

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesMethod(private var appContext: Context) {
    val SHARED_PREFERENCE_NAME = "KabadiwalaSharedPref"
    val TOKEN = "TOKEN"
    val USER_EMAIL = "USER_EMAIL"
    val USER_NAME = "USER_NAME"
    val USER_AVATAR = "USER_AVATAR"
    val USER_ROLE = "USER_ROLE"
    val USER_ID = "USER_ID"
    val USER_SELECTED_ACCOUNT_ID = "USER_SELECTED_ACCOUNT_ID"
    val USER_ACCOUNT_SELECTED_POSITION = "USER_ACCOUNT_SELECTED_POSITION"


    fun getEditor(): SharedPreferences.Editor {
        val sharedpreferences = appContext.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
        return sharedpreferences.edit()
    }

    fun getSharedPreference(): SharedPreferences {
        return appContext.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun getBoolean(name: String?): Boolean {
        val sharedPreferences =
            getSharedPreference(
            )
        return sharedPreferences.getBoolean(name, false)
    }

    fun setStringSharedPreferencehistory(
        name: String?,
        value: Set<String?>?
    ) {

        val settings = appContext.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            0
        )
        val editor = settings.edit()
        // editor.clear();
        editor.putStringSet(name, value)
        editor.apply()
    }

    fun getStringSharedPreferenceshistory(
        name: String?
    ): MutableSet<String>? {
        val settings = appContext.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            0
        )
        return settings.getStringSet(name, null)
    }

    fun setBoolean(
        name: String?,
        value: Boolean
    ) {
        val editor =
            getEditor()
        editor.putBoolean(name, value)
        editor.commit()
    }

    fun getString(name: String?): String? {
        val sharedPreferences =
            getSharedPreference(
            )
        return sharedPreferences.getString(name, "")
    }

    fun setString(
        name: String?,
        value: String?
    ) {
        val editor =
            getEditor()
        editor.putString(name, value)
        editor.commit()
    }

    fun getInt( name: String?): Int {
        val sharedPreferences =
            getSharedPreference(
            )
        return sharedPreferences.getInt(name, 0)
    }

    fun setInt( name: String?, value: Int) {
        //value=value+1;
        val editor =
            getEditor()
        editor.putInt(name, value)
        editor.commit()
    }

    // for username string preferences
    fun setDoubleSharedPreference(
        name: String?,
        value: Double
    ) {
        val settings = appContext.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            0
        )
        val editor = settings.edit()
        editor.putFloat(name, value.toFloat())
        editor.apply()
    }

    fun getDoubleSharedPreferences(
        name: String?
    ): Double {
        val settings = appContext.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            0
        )
        return settings.getFloat(name, 0.0f).toDouble()
    }

    fun setLongSharedPreference(
        name: String?,
        value: Long
    ) {
        val settings = appContext.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            0
        )
        val editor = settings.edit()
        editor.putLong(name, value)
        editor.apply()
    }

    fun getLongSharedPreferences(
        name: String?
    ): Long {
        val settings = appContext.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            0
        )
        return settings.getLong(name, 0L)
    }

    fun clear() {
        val editor =
            getEditor()
        editor.remove(SHARED_PREFERENCE_NAME)
        editor.clear()
        editor.commit()
        appContext.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            0
        ).edit().clear().apply()
    }
}