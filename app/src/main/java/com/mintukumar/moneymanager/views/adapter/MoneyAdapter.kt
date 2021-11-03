package com.mintukumar.moneymanager.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mintukumar.moneymanager.R
import com.mintukumar.moneymanager.model.Money

class MoneyAdapter(val list: List<Money>, val onItemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<MoneyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.money_item_view,parent,false)
        return MoneyViewHolder(view,onItemClickListener)
    }

    override fun onBindViewHolder(holder: MoneyViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}