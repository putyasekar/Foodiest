package com.diki.idn.foodiest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.diki.idn.foodiest.R
import com.diki.idn.foodiest.model.Foods
import kotlinx.android.synthetic.main.item_staggered.view.*

class StaggeredAdapter(private val listStaggered: ArrayList<Foods>) :
    RecyclerView.Adapter<StaggeredAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_staggered, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listStaggered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listStaggered[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(foods: Foods) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(foods.image)
                    .apply(RequestOptions().override(600))
                    .into(iv_staggered)

                title_staggered.text = foods.name
                tv_kind.text = foods.kind
                tv_address_title.text = foods.place
                tv_desc_title.text = foods.desc
            }
        }
    }
}