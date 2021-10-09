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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_online_game, container, false)
        gallowsCardView = v.findViewById(R.id.cardview_gallow)
        crosswordCardView = v.findViewById(R.id.cardview_crossword)

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

                gallowsCardView.setOnClickListener {

                }

                crosswordCardView.setOnClickListener {

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

//    class LoadAnimation(view1: View, view2: View) : AsyncTask<Void, Void, String>() {
//        val v1: View
//        val v2: View
//
//        init {
//            this.v1 = view1
//            this.v2 = view2
//        }
//
//        override fun doInBackground(vararg params: Void?): String {
//            return ""
//        }
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            circularRevealAnimation(view = v1)
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            circularRevealAnimation(v2)
//        }
//    }
}
