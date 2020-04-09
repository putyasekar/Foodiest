package com.diki.idn.foodiest.viewmodel

import com.diki.idn.foodiest.model.food.ApiFoodResponse
import com.diki.idn.foodiest.model.food.MealsItem
import com.diki.idn.foodiest.sources.RetrofitConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BaseMealsViewModel(private val listener: OnFinishedListener<List<MealsItem>>) {
    private val compositeDisposable = CompositeDisposable() //penampung banyak data
    fun fetchMealsData(meals: String) {
        compositeDisposable.add(
            RetrofitConfig.apiServiceClient.getListFood()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleNext, this::handleError)
        )
    }

    private fun handleNext(mealsItem: ApiFoodResponse) {
        val mealsItem = mealsItem.meals
        listener.success(mealsItem as List<MealsItem>)
    }

    private fun handleError(throwable: Throwable) {
        listener.failure(throwable.message)
    }
}