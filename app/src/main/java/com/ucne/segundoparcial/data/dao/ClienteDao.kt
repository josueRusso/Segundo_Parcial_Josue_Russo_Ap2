package com.ucne.segundoparcial.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ucne.segundoparcial.data.entity.ClienteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao{
    @Upsert
    suspend fun upsert(cliente:ClienteEntity)

    @Delete
    suspend fun delete(cliente: ClienteEntity)

    @Query("Select * From cliente")
    fun getAll(): Flow<List<ClienteEntity>>
}