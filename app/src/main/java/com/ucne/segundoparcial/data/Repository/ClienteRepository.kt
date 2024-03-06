package com.ucne.segundoparcial.data.Repository

import com.ucne.segundoparcial.data.dao.ClienteDao
import com.ucne.segundoparcial.data.entity.ClienteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    private val clienteDao: ClienteDao
) {
    suspend fun  upsert(clienteEntity: ClienteEntity){
        clienteDao.upsert(clienteEntity)
    }

    suspend fun  delete(clienteEntity: ClienteEntity){
        clienteDao.delete(clienteEntity)
    }

    fun getCliente():Flow<List<ClienteEntity>>{
        return clienteDao.getAll()
    }
}