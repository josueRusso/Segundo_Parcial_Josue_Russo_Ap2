package com.ucne.segundoparcial.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ucne.segundoparcial.data.repository.ClienteRepository
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao{
    @Upsert
    suspend fun upsert(cliente:ClienteRepository)

    @Delete
    suspend fun delete(cliente: ClienteRepository)

    @Query("Select * From cliente")
    fun getAll(): Flow<List<ClienteRepository>>
}