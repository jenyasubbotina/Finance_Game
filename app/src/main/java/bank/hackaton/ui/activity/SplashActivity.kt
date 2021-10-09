package bank.hackaton.ui.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import bank.hackaton.ui.auth.AuthorizationActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.R
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener


import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bank.hackaton.R.layout.activity_splash)
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