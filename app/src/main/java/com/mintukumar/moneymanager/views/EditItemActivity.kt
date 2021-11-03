package com.mintukumar.moneymanager.views

import com.mintukumar.moneymanager.model.Money
import com.mintukumar.moneymanager.viewmodel.MoneyViewModel
import android.app.DatePickerDialog
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.mintukumar.moneymanager.R
import com.mintukumar.moneymanager.model.MoneyDatabase
import com.mintukumar.moneymanager.repository.MoneyRepo
import com.mintukumar.moneymanager.viewmodel.MoneyViewModelFactory
import kotlinx.android.synthetic.main.activity_add_edit_item.*
import java.text.SimpleDateFormat
import java.util.*


//@AndroidEntryPoint
class EditItemActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private lateinit var pickDate: DatePickerDialog.OnDateSetListener

//    private val moneyViewModel: MoneyViewModel by viewModels()
    lateinit var  moneyViewModel: MoneyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_item)

        PickDate()

        val moneyDatabase = MoneyDatabase.getDatabaseObject(this)
        val moneyDAO = moneyDatabase.getMoneyDAO()
        val moneyRepo = MoneyRepo(moneyDAO)
        val moneyViewModelFactory = MoneyViewModelFactory(moneyRepo)

        moneyViewModel = ViewModelProviders.of(this,moneyViewModelFactory).
        get(MoneyViewModel::class.java)


        ArrayAdapter.createFromResource(this, R.array.category,
            android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }

        if (intent.getSerializableExtra("category") != null){
            editMoney()
        }
        else {
            tvDate.text = dateFormat.format(calendar.time).toString()


            btnSubmit.setOnClickListener {
                if(credentials()){
                    val money = Money(spinner.selectedItem.toString(),
                        etAmount.text.toString().toFloat(),etName.text.toString(),etDescription.text.toString(),
                        tvDate.text.toString())
                    moneyViewModel.addMoney(money)
                    FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }


        tvDate.setOnClickListener {
            val mYear: Int = calendar.get(Calendar.YEAR)
            val mMonth: Int = calendar.get(Calendar.MONTH)
            val mDay: Int = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this,pickDate,mYear,mMonth,mDay).show()
        }

        tvBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }


    private fun editMoney() {
        var money : Money = intent.getSerializableExtra("category") as Money
        if (money.category.equals("Expense"))
            spinner.setSelection(1)
        etAmount.setText(money.amount.toString())
        etDescription.setText(money.description)
        tvDate.text = money.date



        btnSubmit.setOnClickListener {
            if(credentials()){
                money.name = etName.text.toString()
                money.description = etDescription.text.toString()
                money.amount = etAmount.text.toString().toFloat()
                money.category = spinner.selectedItem.toString()
                money.date = tvDate.text.toString()
                moneyViewModel.updateMoney(money)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }



    private fun credentials(): Boolean {
        if(etName.text.toString().isEmpty()){
            etName.setError("Enter Name ")
            return false
        }
        if(etAmount.text.toString().isEmpty()){
            etAmount.setError("Enter the Amount ")
            return false
        }
        if(etDescription.text.toString().isEmpty()){
            etDescription.setError("Enter the Description ")
            return false
        }
        return true
    }

    private fun PickDate(){
        pickDate = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            tvDate.text = dateFormat.format(calendar.time).toString()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}