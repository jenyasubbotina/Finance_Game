package bank.hackaton.ui.activity

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.navigation.findNavController
import bank.hackaton.R

val PERSISTENT_STORAGE_NAME = "persistent_storage_name"
class MainActivity : AppCompatActivity() {
    private lateinit var textGreetings : TextView
    private lateinit var sp : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = Color.WHITE
        initViews()
    }


    override fun onResume() {
        super.onResume()
        val name = sp.getString(PersistentStorage.NAME, "Незнакомец")
        val s = getString(R.string.what_to_do)
        Log.d("string: ", s)
        textGreetings.text = s.substring(0, s.lastIndex) + name + "?"
    }

    private fun initViews(){
        sp = getSharedPreferences(PersistentStorage.PERSISTENT_STORAGE_NAME, MODE_PRIVATE)
        textGreetings = findViewById(R.id.textview_welcome)
    }
}
