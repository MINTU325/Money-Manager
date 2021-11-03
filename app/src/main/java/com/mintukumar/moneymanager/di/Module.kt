package com.mintukumar.moneymanager.di
//
//import android.content.Context
//import androidx.room.Room
//import com.mintukumar.moneymanager.model.MoneyDAO
//import com.mintukumar.moneymanager.model.MoneyDatabase
//import com.mintukumar.moneymanager.repository.MoneyRepo
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object Module {
//
//    @Singleton
//    @Provides
//    fun getDAOObject(@ApplicationContext context: Context): MoneyDAO {
//        return Room.databaseBuilder(context MoneyDatabase::class.java,"money_DB")
//            .build().getMoneyDAO()
//    }
//
//    @Singleton
//    @Provides
//    fun getRepo(moneyDAO: MoneyDAO): MoneyRepo {
//        return MoneyRepo(moneyDAO)
//    }
//
//}