package com.lincollincol.expensestracker.core.data.di

import com.lincollincol.expensestracker.core.data.AccountRepository
import com.lincollincol.expensestracker.core.data.AccountRepositoryImpl
import com.lincollincol.expensestracker.core.data.ExchangeRepository
import com.lincollincol.expensestracker.core.data.ExchangeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository

    @Binds
    fun bindExchangeRepository(impl: ExchangeRepositoryImpl): ExchangeRepository

}