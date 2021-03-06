package bank.hackathon.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SplashActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bank.hackathon.R.layout.activity_splash)
        window.statusBarColor = Color.WHITE
        mAuth = Firebase.auth
        when(mAuth.currentUser){
            null -> toLoginActivity()
            else -> toMainActivity()
        }
    }

    private fun toMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        finish()
        startActivity(intent)
    }

    private fun toLoginActivity() {
        val intent = Intent(applicationContext, AuthorizationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        finish()
        startActivity(intent)
    }
}
