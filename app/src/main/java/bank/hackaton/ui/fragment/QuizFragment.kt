package bank.hackaton.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import bank.hackaton.R
import bank.hackaton.model.CaseModel
import bank.hackaton.ui.adapter.CardStackAdapter
import com.yuyakaido.android.cardstackview.*

class QuizFragment : Fragment(), CardStackListener {

    private lateinit var cardStackView: CardStackView
    private val manager by lazy { CardStackLayoutManager(requireContext(), this) }
    private val adapter by lazy { CardStackAdapter(createCases()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_quiz, container, false)
        cardStackView = v.findViewById(R.id.cardstack)
        initialize()
        return v
    }

    fun initialize() {
       // val adapter: CardStackAdapter(createCases())
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    fun createCases(): List<CaseModel> {
        val temp = ArrayList<CaseModel>()
        temp.add(CaseModel("Yasaka Shrine", "Kyoto", "https://source.unsplash.com/Xq1ntWruZQI/600x800", true))
        temp.add(CaseModel("Fushimi Inari Shrine", "Kyoto", "https://source.unsplash.com/NYyCqdBOKwc/600x800", true))
        temp.add(CaseModel("Bamboo Forest", "Kyoto", "https://source.unsplash.com/buF62ewDLcQ/600x800", true))
        return temp
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }
}
