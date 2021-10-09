package bank.hackathon.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import bank.hackathon.R
import bank.hackathon.utils.SessionManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
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
    private lateinit var nameText: TextInputEditText
    private lateinit var db: DatabaseReference
    private lateinit var sm: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)
        initViews(root)

        registerButton.setOnClickListener {
            val name = loginText.text.toString()
            val password = passwordText.text.toString()
            var personName = nameText.text.toString().trim()

            if (personName == "")
                personName = "Незнакомец"

            if (name == "" && password == "") {
                loginText.error = getString(R.string.error_login)
                passwordText.error = getString(R.string.error_password)
            }
            else if (name == "") {
                loginText.error = getString(R.string.error_login)
            }
            else if (password == "") {
                passwordText.error = getString(R.string.error_password)
            }
            else {
                mAuth.createUserWithEmailAndPassword(name, password)
                    .addOnCompleteListener(activity as Activity)
                    { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Вы успешно зарегистрировались!",
                                Toast.LENGTH_SHORT
                            ).show()
                            toMain()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                context,
                                "Что-то пошло не так, возможно вы ввели некорректные данные",
                                Toast.LENGTH_SHORT
                            ).show()
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
        nameText = root.findViewById(R.id.name_person_login)
        db = Firebase.database.reference
        loginText = root.findViewById(R.id.username_login)
        sm = SessionManager(requireContext())
    }

    private fun toMain() {
        val intent = Intent(context, bank.hackathon.ui.activity.MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
