package com.diki.idn.foodiest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.diki.idn.foodiest.R
import com.diki.idn.foodiest.adapter.ListRecyclerAdapter
import com.diki.idn.foodiest.model.Foods
import kotlinx.android.synthetic.main.activity_best_seller.*

class BestSellerActivity : AppCompatActivity() {
    private val listFoodA = ArrayList<Foods>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_seller)

        rv_best_seller.setHasFixedSize(true)

        listFoodA.addAll(getListFood())
        supportActionBar?.hide()
        showRecyclerList()
    }

    private fun getListFood(): ArrayList<Foods> {
        val dataName = resources.getStringArray(R.array.food_name)
        val dataPlace = resources.getStringArray(R.array.place)
        val dataKind = resources.getStringArray(R.array.kind)
        val dataDesc = resources.getStringArray(R.array.desc)
        val dataImage = resources.obtainTypedArray(R.array.image)

        val listFood = ArrayList<Foods>()

        for (position in dataName.indices) {
            val food = Foods(
                dataName[position],
                dataPlace[position],
                dataKind[position],
                dataDesc[position],
                dataImage.getResourceId(position, -1)
            )
            listFood.add(food)
        }
        return listFood

    }

    private fun showRecyclerList() {
        rv_best_seller.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        rv_best_seller.adapter = ListRecyclerAdapter(listFoodA)
    }

}
