package bank.hackathon.utils

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.InputMethodManager
import bank.hackathon.utils.Constants.Constants
import bank.hackathon.utils.Constants.Constants.MY_SETTINGS

class SessionManager(c: Context) {
    var context: Context? = null
    private lateinit var prefs: SharedPreferences
    init {
        this.context = c
    }

    fun getUserName(): String? {
        prefs = context!!.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE)
        return prefs.getString(Constants.USER_NAME, "")
    }

    fun saveUsername(username: String?) {
        prefs = context!!.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(Constants.USER_NAME, username)
        System.out.println(Constants.USER_NAME + " " + username)
        editor.apply()
    }

    fun getUserId(): String? {
        prefs = context!!.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE)
        return prefs.getString(Constants.USER_ID, "")
    }

    fun saveUserId(id: String?) {
        prefs = context!!.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(Constants.USER_ID, id)
        editor.apply()
    }

    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
