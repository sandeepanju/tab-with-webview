package com.example.assignmenttest.util

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor
  companion object{
      private const val PREF_NAME = "KodeinOwnPref"
      private const val IS_TRUE = "IS_TRUE"

  }
    init {
        val PRIVATE_MODE = 0
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }
    fun setBoolean(isTrue: Boolean) {
        editor.putBoolean(IS_TRUE, isTrue)
        editor.apply()
    }

    val isFromForgotClick: Boolean
        get() = pref.getBoolean(IS_TRUE,false)
}
