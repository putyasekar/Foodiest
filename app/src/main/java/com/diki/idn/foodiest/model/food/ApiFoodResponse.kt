package com.diki.idn.foodiest.model.food

import com.google.gson.annotations.SerializedName

data class ApiFoodResponse(

    @field:SerializedName("meals")
    val meals: List<MealsItem?>? = null
)