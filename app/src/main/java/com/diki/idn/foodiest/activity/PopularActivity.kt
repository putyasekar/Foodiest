package com.diki.idn.foodiest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.diki.idn.foodiest.R
import com.diki.idn.foodiest.adapter.StaggeredAdapter
import com.diki.idn.foodiest.fragment.HomeFragment
import com.diki.idn.foodiest.model.Foods
import kotlinx.android.synthetic.main.activity_popular.*
import kotlinx.android.synthetic.main.fragment_home.*

class PopularActivity : AppCompatActivity() {
    private val listFood = ArrayList<Foods>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        iv_home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        all_popular.setHasFixedSize(true)

        listFood.addAll(getListFood())
        supportActionBar?.hide()
        showRecyclerGrid()
    }

    fun getListFood(): ArrayList<Foods> {
        val dataName = resources.getStringArray(R.array.food_name)
        val dataPlace = resources.getStringArray(R.array.place)
        val dataKind = resources.getStringArray(R.array.kind)
        val dataDesc = resources.getStringArray(R.array.desc)
        val dataImage =
            resources.obtainTypedArray(R.array.image) //karna dia gambar jadi pakenya obtain

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

    private fun showRecyclerGrid() {
        val layoutManagerStaggered =
            StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        all_popular.layoutManager = layoutManagerStaggered
        all_popular.adapter = StaggeredAdapter(listFood)
    }
}