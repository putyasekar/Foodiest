package com.diki.idn.foodiest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.diki.idn.foodiest.R
import com.diki.idn.foodiest.fragment.HomeFragment
import com.diki.idn.foodiest.model.Foods
import kotlinx.android.synthetic.main.activity_detail_popular_food.*
import kotlinx.android.synthetic.main.activity_popular.*

class DetailPopularFoodActivity : AppCompatActivity() {

    companion object {
        const val KEY_POPULAR_FOOD = "key_popular_food"
    }

    private var foods: Foods? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_popular_food)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        iv_backstage.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        foods = intent.getParcelableExtra(KEY_POPULAR_FOOD)

        tv_name_detail_in_populer.text = foods?.name
        tv_place_in_detail.text = foods?.place
        tv_kind_in_detail.text = foods?.kind
        tv_desc_in_detail.text = foods?.desc
        Glide.with(this).load(foods?.image).apply(RequestOptions().override(600))
            .into(iv_image_detail)
    }
}
