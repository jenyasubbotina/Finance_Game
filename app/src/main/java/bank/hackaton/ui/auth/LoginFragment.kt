package bank.hackaton.UI

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import bank.hackaton.R
import bank.hackaton.ui.activity.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private lateinit var loginButton: MaterialButton
    private lateinit var loginText: TextInputEditText
    private lateinit var passwordText: TextInputEditText
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_login, container, false)
        initViews(root)
        if (mAuth.currentUser != null) {
            toMain()
        } else {
            loginButton.setOnClickListener {
                val name = loginText.text.toString()
                val password = passwordText.text.toString()
                Log.d("login:", name)
                Log.d("password:", password)
                if (name == "" && password == "") {
                    loginText.error = getString(R.string.error_login)
                    passwordText.error = getString(R.string.error_password)
                } else if (name == "") {
                    loginText.error = getString(R.string.error_login)
                } else if (password == "") {
                    passwordText.error = getString(R.string.error_password)
                } else {
                    mAuth.signInWithEmailAndPassword(name, password)
                        .addOnCompleteListener(activity as Activity)
                        { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Logged", "signInWithEmail:success")
                                val user = mAuth.currentUser
                                toMain()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Logged", "signInWithEmail:failure", task.exception)
                                Toast.makeText(
                                    context, getString(R.string.authetication_failed),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }
        return root
    }

    private fun toMain() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    private fun initViews(root: View) {
        mAuth = Firebase.auth
        loginButton = root.findViewById(R.id.login_button)
        passwordText = root.findViewById(R.id.password_login)
        loginText = root.findViewById(R.id.username_login)
    }


}