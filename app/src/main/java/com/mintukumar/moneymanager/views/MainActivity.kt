package com.mintukumar.moneymanager.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mintukumar.moneymanager.views.adapter.FragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mintukumar.moneymanager.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addMoneyTask.setOnClickListener {
            startActivity(Intent(this, EditItemActivity::class.java))
        }

        viewPager.adapter = FragmentAdapter(supportFragmentManager,lifecycle)
        TabLayoutMediator(tabLayout,viewPager,object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                when(position){
                    0 -> tab.text = "Income"
                    1 -> tab.text = "Expense"



                }
            }
        }).attach()

    }
}