package com.diki.idn.foodiest.viewmodel

import com.diki.idn.foodiest.AppBaseInterface
import com.diki.idn.foodiest.model.food.MealsItem

class MealsViewModel(private var view: AppBaseInterface<List<MealsItem>>?) :
    MealsViewModelInterface, OnFinishedListener<List<MealsItem>> {
    override fun success(data: List<MealsItem>) {
        view?.hideProgressBar()
        view?.showData()
    }

    override fun failure(message: String?) {
        view?.hideProgressBar()
        view?.showError(message)
    }
}