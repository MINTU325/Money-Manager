package com.mintukumar.moneymanager.repository

import androidx.lifecycle.LiveData
import com.mintukumar.moneymanager.model.Money
import com.mintukumar.moneymanager.model.MoneyDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoneyRepo ( val moneyDAO: MoneyDAO) {

    fun addMoneyToRoom(money: Money){
        CoroutineScope(Dispatchers.IO).launch {
            moneyDAO.insert(money)
        }
    }

    fun updateMoney(money: Money){
        CoroutineScope(Dispatchers.IO).launch {
            moneyDAO.update(money)
        }
    }

    fun deleteMoney(money: Money){
        CoroutineScope(Dispatchers.IO).launch {
            moneyDAO.delete(money)
        }
    }

    fun getAllMoney(category: String): LiveData<List<Money>>{
        return moneyDAO.get(category)
    }

}