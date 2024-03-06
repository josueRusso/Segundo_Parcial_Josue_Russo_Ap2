package com.ucne.segundoparcial.di

import android.content.Context
import androidx.room.Room
import com.ucne.segundoparcial.data.dataBase.ClienteDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesClienteDatabase(@ApplicationContext appContext: Context): ClienteDataBase =
        Room.databaseBuilder(appContext, ClienteDataBase::class.java,"Cliente.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideClienteDao(db:ClienteDataBase) = db.ClienteDao()
}