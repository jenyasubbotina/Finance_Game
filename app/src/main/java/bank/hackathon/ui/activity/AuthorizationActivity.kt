package bank.hackathon.ui.activity


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import bank.hackathon.R
import bank.hackathon.ui.fragment.LoginFragment
import bank.hackathon.ui.fragment.RegisterFragment


private const val TOTAL = 2
class AuthorizationActivity : FragmentActivity() {

    private lateinit var viewPager2 : ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        viewPager2 = findViewById(R.id.pager)
        window.statusBarColor = Color.WHITE
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager2.adapter = pagerAdapter
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = TOTAL

        override fun createFragment(position: Int): Fragment {
            when(position){
                0 -> return LoginFragment()
                1 -> return RegisterFragment()
            }
            return LoginFragment()
        }
    }
}