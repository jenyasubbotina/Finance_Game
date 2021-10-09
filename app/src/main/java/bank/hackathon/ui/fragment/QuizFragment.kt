package bank.hackathon.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import bank.hackathon.R
import bank.hackathon.ui.adapter.CardStackAdapter
import bank.hackathon.model.CaseModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.yuyakaido.android.cardstackview.*
import org.w3c.dom.Text

class QuizFragment : Fragment(), CardStackListener {

    private lateinit var cardStackView: CardStackView
    private val manager by lazy { CardStackLayoutManager(requireContext(), this) }
    private lateinit var adapter: CardStackAdapter
    private lateinit var gainMoneyTextView: TextView
    private var money: Long = 0L
    private var curPos = 0
    private lateinit var cases: List<CaseModel>
    private var plusMoney = 10L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_quiz, container, false)
        cardStackView = v.findViewById(R.id.cardstack)
        gainMoneyTextView = v.findViewById(R.id.textview_gain_money)
        adapter = CardStackAdapter(createCases())
        initialize()
        return v
    }

    fun initialize() {
        setMoney(money)
        cases = createCases()
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
        temp.add(CaseModel("Газ и холода", "Страна А переживает тяжелый кризис и дефицит газа. Цены на коммунальные услуги растут, как и недовольство потребителей. \n" +
                "\n" +
                "В этой тяжелой ситцуации Компания Х является важным и единственным стабильным поставщиком сырья в страну А.  В том числе поэтому за последние несколько месяцев акции X значительно выросли.\n" +
                "\n" +
                "Компания объявила, что готовится к увеличению поставок. Перед тобой стоит выбор: купить акции Х или нет. Свайпни вправо, если считаешь такую покупку целесообразной, влево - иначе.",true, "В описанных условиях покупка акций Х может быть весьма выгодна инвесторам. Мы не можем предсказать точного их роста, но видим предпосылки к подобному изменению цены. По той же причине держателям акций этой компании, планирующим их продать, стоило бы повременить и тщательно проанализировать ситуацию на рынке, чтобы получить максимальную прибыль от сделки."))
        temp.add(CaseModel("Хитрецы", "собралась как-то вечером компания друзей-инвесторов и долго думала, какую бы шалость сотворить. В итоге они скооперировались и купили много-много акций одной компании. Для фондового рынка рынка это было весьма шокирующе. Как ты думаешь, что стало с акциями этой компании? \n" +
                "\n" +
                "свайп вправо - возросли, влево - просели.", true, "такая ситуация и правда произошла с американской компанией GameStop, занимающейся продажей компьютерных игр и приставок. Массовая скупка акций привела к ажиотажу на рынке и десятикратному росту их цены."))
        temp.add(CaseModel("Covid-19, the destroyer", "Х - крупная международная компания, занимающаяся продажей недвижимости. В период пандемии ей, как и многим другим было нелегко. Прошло 1.5 года и компания объявила о выплате дивидендов своим акционерам. О чем это может говорить? \n" +
                "\n" +
                "свайп вправо - Х постепенно восстанавливается после пандемии, влево - компания переживает тяжелый кризис",true, "В этом кейсе выплата дивидендов говорит нам о восстановлении и процветании Х. Дела компании пошли в гору, а недвижимость вновь стала востребована. "))
        return temp
    }

    fun setMoney(m: Long) {
        gainMoneyTextView.setText(getString(R.string.gain_money, m))
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {
        if (direction == Direction.Right) {
            if (cases[curPos].answer) {
                Snackbar.make(requireContext(), gainMoneyTextView, "Вы заработали " + plusMoney + " монет", Snackbar.LENGTH_SHORT).show()
                money += plusMoney
            } else {
                Snackbar.make(requireContext(), gainMoneyTextView, "Вы потеряли " + plusMoney + " монет", Snackbar.LENGTH_SHORT).show()
                money -= plusMoney
            }
        }
        else if (direction == Direction.Left) {
            if (!cases[curPos].answer) {
                Snackbar.make(requireContext(), gainMoneyTextView, "Вы заработали " + plusMoney + " монет", Snackbar.LENGTH_SHORT).show()
                money += plusMoney
            } else {
                Snackbar.make(requireContext(), gainMoneyTextView, "Вы потеряли " + plusMoney + " монет", Snackbar.LENGTH_SHORT).show()
                money -= plusMoney
            }
        }
        setMoney(money)
        showBottomSheet(cases[curPos].answerStr)
    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {
        curPos = position
    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

    fun showBottomSheet(s: String) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_answer)
        val text = bottomSheetDialog.findViewById<TextView>(R.id.textview_answer)
        text!!.setText(s)
        bottomSheetDialog.show()
    }
}
