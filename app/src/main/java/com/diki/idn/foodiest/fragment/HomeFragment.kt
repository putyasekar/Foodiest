package com.diki.idn.foodiest.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.diki.idn.foodiest.R
import com.diki.idn.foodiest.activity.BestSellerActivity
import com.diki.idn.foodiest.activity.DetailPopularFoodActivity
import com.diki.idn.foodiest.activity.PopularActivity
import com.diki.idn.foodiest.adapter.FoodAdapter
import com.diki.idn.foodiest.model.Foods
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_popular.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    //    private val foodList = ArrayList<Foods>()
    private lateinit var popularFoodAdapter: FoodAdapter

    companion object {
        fun defaultFragment(): HomeFragment {
            val homeFragment = HomeFragment()
            val bundle = Bundle()
            homeFragment.arguments = bundle
            return homeFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    val imageContentSlider = intArrayOf(
        R.drawable.photo_1,
        R.drawable.photo_2,
        R.drawable.photo_3,
        R.drawable.photo_4,
        R.drawable.photo_5,
        R.drawable.photo_6
    )

    val imageContentListener: ImageListener =
        ImageListener { position, imageView -> imageView?.setImageResource(imageContentSlider[position]) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val carouselView = is_main as CarouselView
        carouselView.setImageListener(imageContentListener)
        carouselView.pageCount = imageContentSlider.count()
//        foodList.addAll(getListFood())
        showRecyclerList()

        see_more.setOnClickListener {
            val intent = Intent(context, PopularActivity::class.java)
            startActivity(intent)
        }

        see_more2.setOnClickListener {
            val intent = Intent(context, BestSellerActivity::class.java)
            startActivity(intent)
        }

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

    //attach adapter
    private fun showRecyclerList() {
        popularFoodAdapter = FoodAdapter { showSelected(it) }
        popularFoodAdapter.notifyDataSetChanged()
        popularFoodAdapter.setData(getListFood())
        rv_popular.setHasFixedSize(true)
        rv_popular.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        val listFoodAdapter = FoodAdapter(foodList)
        rv_popular.adapter = popularFoodAdapter
    }

    private fun showSelected(it: Foods) {
        val page = Intent(context, DetailPopularFoodActivity::class.java)
        page.putExtra(DetailPopularFoodActivity.KEY_POPULAR_FOOD, it)
        startActivity(page)
    }
}