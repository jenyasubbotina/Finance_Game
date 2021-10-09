package bank.hackaton.ui.fragment

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import bank.hackaton.R
import bank.hackaton.utils.OnSwipeTouchListener
import java.lang.Exception


class OnlineGameFragment : Fragment() {

    private lateinit var gallowsCardView: CardView
    private lateinit var crosswordCardView: CardView
    private lateinit var quizCardView: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_online_game, container, false)
        gallowsCardView = v.findViewById(R.id.cardview_gallow)
        crosswordCardView = v.findViewById(R.id.cardview_crossword)
        quizCardView = v.findViewById(R.id.cardview_quest)

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
                circularRevealAnimation(gallowsCardView)
                Handler(Looper.getMainLooper()).postDelayed({
                    circularRevealAnimation(crosswordCardView)
                }, 600)
                Handler(Looper.getMainLooper()).postDelayed({
                    circularRevealAnimation(quizCardView)
                }, 1200)

                gallowsCardView.setOnClickListener {

                }

                crosswordCardView.setOnClickListener {

                }

                quizCardView.setOnClickListener {

                }
            }
        })
        return v
    }

    fun circularRevealAnimation(view: View) {
        view.visibility = View.INVISIBLE
        val cx = view.width / 2
        val cy = view.height / 2
        val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius)
        anim.duration = 1000
        view.visibility = View.VISIBLE
        anim.start()
    }
}
