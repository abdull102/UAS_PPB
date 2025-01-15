package com.example.reeltime.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.example.reeltime.model.User

class LoginData {

    private val prefsName = "LoginPrefs"
    private val userKey = "user_data"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    }

    // Menangani error saat menyimpan data login
    fun setLoginData(context: Context, userdata: User) {
        val sharedPreferences = getSharedPreferences(context)
        val editor = sharedPreferences.edit()
        try {
            val userJson = Gson().toJson(userdata)
            editor.putString(userKey, userJson)
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()  // Debugging purpose
        }
    }

    // Menangani error saat mengambil data login
    fun getLoginData(context: Context): User? {
        val sharedPreferences = getSharedPreferences(context)
        val userJson = sharedPreferences.getString(userKey, null)
        return try {
            if (userJson != null) {
                Gson().fromJson(userJson, User::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()  // Debugging purpose
            null
        }
    }

    // Clear login data (optional)
    fun clearLoginData(context: Context) {
        val sharedPreferences = getSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.remove(userKey)
        editor.apply()
    }
}
