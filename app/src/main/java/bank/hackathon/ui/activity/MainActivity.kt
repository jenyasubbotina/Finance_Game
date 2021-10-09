package bank.hackathon.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import bank.hackathon.R
import bank.hackathon.utils.SessionManager

class MainActivity : AppCompatActivity() {
    private lateinit var textGreetings : TextView
    private lateinit var sm : SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = Color.WHITE
        initViews()
    }

    private fun initViews() {
        sm = SessionManager(this)
    }
}
