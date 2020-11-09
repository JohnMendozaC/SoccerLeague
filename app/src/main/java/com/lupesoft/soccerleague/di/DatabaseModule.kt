package com.lupesoft.soccerleague.di

import android.content.Context
import com.lupesoft.data.AppDataBase
import com.lupesoft.soccerleague.data.country.CountryDao
import com.lupesoft.soccerleague.data.league.LeagueDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase =
        AppDataBase.getInstance(context)

    @Provides
    fun provideCountryDao(appDataBase: AppDataBase): CountryDao = appDataBase.countryDao()

    @Provides
    fun provideLeagueDao(appDataBase: AppDataBase): LeagueDao = appDataBase.leagueDao()
}