package bank.hackathon.ui.adapter

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bank.hackathon.R
import bank.hackathon.model.CaseModel


class CardStackAdapter(
    private var cases: List<CaseModel> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.case_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val case = cases[position]
        holder.name.text = case.name
        holder.description.text = case.description
        holder.description.setMovementMethod(ScrollingMovementMethod())
    }

    override fun getItemCount(): Int {
        return cases.size
    }

    fun setCases(spots: List<CaseModel>) {
        this.cases = spots
        notifyDataSetChanged()
    }

    fun getCases(): List<CaseModel> {
        return cases
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.textview_name)
        var description: TextView = view.findViewById(R.id.textview_description)
//        var image: ImageView = view.findViewById(R.id.imageview_case)
    }
}
