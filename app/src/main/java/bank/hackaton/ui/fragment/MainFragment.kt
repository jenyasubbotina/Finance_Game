package bank.hackaton.ui.fragment

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import bank.hackaton.R


class MainFragment : Fragment() {

    lateinit var navController: NavController
    lateinit var cardViewGames: CardView
    lateinit var crosswordImageView: ImageView
    lateinit var gallowsImageView: ImageView
    lateinit var questImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        navController = NavHostFragment.findNavController(this)

        crosswordImageView = v.findViewById(R.id.imageview_crossword)
        gallowsImageView = v.findViewById(R.id.imageview_gallows)
        questImageView = v.findViewById(R.id.imageview_quest)

        cardViewGames = v.findViewById(R.id.cardview_games)

        cardViewGames.setOnClickListener {
            navController.navigate(MainFragmentDirections.actionMainFragmentToOnlineGameFragment())
        }

        v.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            @SuppressLint("ClickableViewAccessibility")
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onLayoutChange(
                v: View,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                v.removeOnLayoutChangeListener(this)

                Handler(Looper.getMainLooper()).postDelayed({
                    transitionAnimation(container!!, crosswordImageView)
                }, 300)

                Handler(Looper.getMainLooper()).postDelayed({
                    transitionAnimation(container!!, gallowsImageView)
                }, 900)

                Handler(Looper.getMainLooper()).postDelayed({
                    transitionAnimation(container!!, questImageView)
                }, 1500)
            }
        })

        return v
    }

    fun transitionAnimation(parent: ViewGroup, view: View) {
        val transition: Transition = Fade()
        transition.setDuration(600)
        transition.addTarget(view)

        TransitionManager.beginDelayedTransition(parent, transition)
        view.setVisibility(View.VISIBLE)
    }
}
