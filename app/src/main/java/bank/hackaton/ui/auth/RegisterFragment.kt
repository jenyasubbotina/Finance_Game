package bank.hackaton.UI

import android.app.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import bank.hackaton.R
import bank.hackaton.ui.activity.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialTextInputPicker
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {


    private lateinit var mAuth: FirebaseAuth
    private lateinit var registerButton: MaterialButton
    private lateinit var loginText: TextInputEditText
    private lateinit var passwordText: TextInputEditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)
        initViews(root)
        if (mAuth.currentUser != null) {
            toMain()
        } else {
            registerButton.setOnClickListener {
                val name = loginText.text.toString()
                val password = passwordText.text.toString()
                Log.d("login:", name)
                Log.d("password:", password)
                if (name == "" && password == "") {
                    loginText.error = "You haven't filled login"
                    passwordText.error = "You haven't filled text"
                } else if (name == "") {
                    loginText.error = "You haven't filled login"
                } else if (password == "") {
                    passwordText.error = "You haven't filled text"
                } else {
                    mAuth.createUserWithEmailAndPassword(name, password)
                        .addOnCompleteListener(activity as Activity)
                        { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Logged", "signInWithEmail:success")
                                val user = mAuth.currentUser
                                Toast.makeText(
                                    context,
                                    "You authorized successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                toMain()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Logged", "signInWithEmail:failure", task.exception)
                                Toast.makeText(
                                    context, "Something went wrong try again",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }
        return root
    }

    private fun initViews(root: View) {
        mAuth = Firebase.auth
        registerButton = root.findViewById(R.id.register_button)
        passwordText = root.findViewById(R.id.password_login)
        loginText = root.findViewById(R.id.username_login)
    }

    private fun toMain() {
        val intent = Intent(context, bank.hackaton.ui.activity.MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}