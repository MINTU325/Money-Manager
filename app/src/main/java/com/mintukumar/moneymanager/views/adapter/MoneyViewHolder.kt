package com.mintukumar.moneymanager.views.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mintukumar.moneymanager.R
import com.mintukumar.moneymanager.model.Money
import kotlinx.android.synthetic.main.money_item_view.view.*

class MoneyViewHolder(itemView: View, val onItemClickListener: OnItemClickListener)
    : RecyclerView.ViewHolder(itemView){

    fun setData(money: Money){
        itemView.apply {
            tvAmount.text = money.amount.toString()
            if (money.category.equals("Income"))
                tvAmount.setTextColor(ContextCompat.getColor(context, R.color.green))
            else tvAmount.setTextColor(ContextCompat.getColor(context, R.color.red))
            tvDescription.text = money.description
            tvDate.text = money.date
            tvName.text = money.name
        }
        itemView.item.setOnClickListener {
            onItemClickListener.onClick(money)
        }
    }

}