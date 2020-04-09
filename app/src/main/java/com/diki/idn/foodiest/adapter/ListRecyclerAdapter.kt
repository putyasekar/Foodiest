package com.diki.idn.foodiest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.diki.idn.foodiest.R
import com.diki.idn.foodiest.model.Foods
import kotlinx.android.synthetic.main.item_row_recyclerview.view.*

class ListRecyclerAdapter(private val listRecycler: ArrayList<Foods>) :
    RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row_recyclerview, parent, false)
        return ListRecyclerAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = listRecycler.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listRecycler[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(foods: Foods) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(foods.image)
                    .apply(RequestOptions().override(600))
                    .into(food_icon)

                food_name.text = foods.name
                food_address.text = foods.place
                food_kind.text = foods.kind
                food_desc.text = foods.desc
            }
        }
    }
}