package com.diki.idn.foodiest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.diki.idn.foodiest.R
import com.diki.idn.foodiest.model.Foods
import kotlinx.android.synthetic.main.item_row.view.*
import kotlinx.android.synthetic.main.item_row.view.tv_kind

class FoodAdapter(private val listener: (Foods) -> Unit) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() { // viewholder untuk set data ke view kita

    private val listFoods = ArrayList<Foods>()

    //setter
    fun setData(items: ArrayList<Foods>) {
        listFoods.clear()
        listFoods.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listFoods.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFoods[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(foods: Foods, listener: (Foods) -> Unit) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(foods.image)
                    .apply(RequestOptions())
                    .override(300)
                    .into(iv_popular)

                tv_title.text = foods.name
                tv_place.text = foods.place
                tv_kind.text = foods.kind
                itemView.setOnClickListener { listener(foods) }
            }
        }
    }
}