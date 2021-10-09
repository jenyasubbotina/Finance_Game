package bank.hackaton.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import bank.hackaton.R
import com.squareup.picasso.Picasso

//class CardStackAdapter(
//    //private var spots: List<Spot> = emptyList()
//) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return ViewHolder(inflater.inflate(R.layout.case_item_layout, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//       // val spot = spots[position]
//        holder.name.text = "${spot.id}. ${spot.name}"
//
////        Picasso.get()
////            .load(foodAds[position].image)
////            .noPlaceholder()
////            .fit()
////            .centerCrop()
////            .into(holder.foodImage)
//    }
//
//    override fun getItemCount(): Int {
//     //   return spots.size
//    }
//
//    fun setSpots(spots: List<Spot>) {
//        this.spots = spots
//    }
//
//    fun getSpots(): List<Spot> {
//        return spots
//    }

//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val name: TextView = view.findViewById(R.id.textview_name)
//        var description: TextView = view.findViewById(R.id.textview_description)
//        var image: ImageView = view.findViewById(R.id.imageview_case)
//    }
//}
