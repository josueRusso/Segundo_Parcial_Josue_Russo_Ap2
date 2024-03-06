package com.ucne.segundoparcial.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.segundoparcial.data.dao.ClienteDao
import com.ucne.segundoparcial.data.repository.ClienteRepository


@Database(entities = [ClienteRepository::class], version = 1)
abstract class ClienteDataBase : RoomDatabase(){
    abstract fun ClienteDao(): ClienteDao
}