package bank.hackaton.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import bank.hackaton.R
import bank.hackaton.model.CaseModel
import com.squareup.picasso.Picasso

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

        Picasso.get()
            .load(case.imageUrl)
            .noPlaceholder()
            .fit()
            .centerCrop()
            .into(holder.image)
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
        var image: ImageView = view.findViewById(R.id.imageview_case)
    }
}
