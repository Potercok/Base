package com.example.base.io.response.authHelpers

import android.content.Context
import com.example.base.util.PreferenceHelper

class TokenController {
    fun getToken(context: Context): String {
        val preferences = PreferenceHelper.defaultPrefs(context)
        return preferences.getString("jwt","")?:""
    }

    fun storeToke(context: Context, jwt: String){
        val preferences = PreferenceHelper.defaultPrefs(context)
        val editor = preferences.edit()
        editor.putString("jwt",jwt)
        editor.apply()
    }

}