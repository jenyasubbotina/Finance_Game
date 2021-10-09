package bank.hackaton.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import bank.hackaton.R

class MainFragment : Fragment() {

    lateinit var navController: NavController
    lateinit var cardViewGames: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        navController = NavHostFragment.findNavController(this)
        cardViewGames = v.findViewById(R.id.cardview_games)

        cardViewGames.setOnClickListener {
            navController.navigate(MainFragmentDirections.actionMainFragmentToOnlineGameFragment())
        }
        return v
    }
}
