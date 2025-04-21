package com.lincollincol.expensestracker.core.database.di

import android.content.Context
import androidx.room.Room
import com.lincollincol.expensestracker.core.database.ETDatabase
import com.lincollincol.expensestracker.core.database.dao.AccountDao
import com.lincollincol.expensestracker.core.database.dao.ExchangeRateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ETDatabase = Room.databaseBuilder(context, ETDatabase::class.java, "et-db").build()

    @Provides
    @Singleton
    fun provideAccountDao(database: ETDatabase): AccountDao = database.accountDao()

    @Provides
    @Singleton
    fun provideExchangeRate(database: ETDatabase): ExchangeRateDao = database.exchangeRateDao()

}