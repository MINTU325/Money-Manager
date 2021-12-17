package com.mintukumar.moneymanager.views.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintukumar.moneymanager.R
import com.mintukumar.moneymanager.model.Money
import com.mintukumar.moneymanager.model.MoneyDatabase
import com.mintukumar.moneymanager.repository.MoneyRepo
import com.mintukumar.moneymanager.viewmodel.MoneyViewModel
import com.mintukumar.moneymanager.viewmodel.MoneyViewModelFactory
import com.mintukumar.moneymanager.views.EditItemActivity
import com.mintukumar.moneymanager.views.adapter.MoneyAdapter
import com.mintukumar.moneymanager.views.adapter.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_income.*

@AndroidEntryPoint
class ExpenseFragment : Fragment(R.layout.fragment_expense), OnItemClickListener {

    private var listMoney = mutableListOf<Money>()

    private val moneyViewModel: MoneyViewModel by viewModels()
//    lateinit var  moneyViewModel: MoneyViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val moneyDatabase = MoneyDatabase.getDatabaseObject(context)
//        val moneyDAO = moneyDatabase.getMoneyDAO()
//        val moneyRepo = MoneyRepo(moneyDAO)
//        val moneyViewModelFactory = MoneyViewModelFactory(moneyRepo)
//
//        moneyViewModel = ViewModelProviders.of(this,moneyViewModelFactory).
//        get(MoneyViewModel::class.java)

        recyclerView.adapter = MoneyAdapter(listMoney,this)
        recyclerView.layoutManager = LinearLayoutManager(context)

        getDataFromDB()

    }

    /* Continuously observing live data from room database and showing it on recycler view */

    private fun getDataFromDB(){
        moneyViewModel.getAllMoney("Expense").observe(viewLifecycleOwner, Observer {
            listMoney.clear()
            listMoney.addAll(it)
            recyclerView.adapter?.notifyDataSetChanged()
        })
    }

    /* On click of any item in the recycler view showing custom alert dialog box in which asking for
    * edition or deletion of particular item */

    override fun onClick(money: Money) {
        val alertDialog = AlertDialog.Builder(context).create()
        val customLayout = layoutInflater.inflate(R.layout.alert_dialog_view, null)
        alertDialog.setView(customLayout)
        alertDialog.show()
        val mBtnEdit = customLayout.findViewById<Button>(R.id.btnEdit)
        val mBtnDelete = customLayout.findViewById<Button>(R.id.btnDelete)
        mBtnEdit.setOnClickListener {
            val intent = Intent(context, EditItemActivity::class.java)
            intent.putExtra("category",money)
            startActivity(intent)
        }

        mBtnDelete.setOnClickListener {
            moneyViewModel.deleteMoney(money)
            alertDialog.dismiss()
        }
    }

}