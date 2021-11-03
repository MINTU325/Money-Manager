package com.mintukumar.moneymanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mintukumar.moneymanager.model.Money
import com.mintukumar.moneymanager.repository.MoneyRepo

//@HiltViewModel
class MoneyViewModel(val repo: MoneyRepo): ViewModel() {

    fun addMoney(money: Money){
        repo.addMoneyToRoom(money)
    }

    fun updateMoney(money: Money){
        repo.updateMoney(money)
    }

    fun deleteMoney(money: Money){
        repo.deleteMoney(money)
    }

    fun getAllMoney(category: String): LiveData<List<Money>>{
        return repo.getAllMoney(category)
    }

}