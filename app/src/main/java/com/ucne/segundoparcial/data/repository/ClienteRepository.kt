package com.ucne.segundoparcial.data.repository

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("cliente")
data class ClienteRepository(
    @PrimaryKey(autoGenerate = true)
    val codigoCliente: Int = 0,
    val nombres: String = "",
    val direccion: String = "",
    val telefono: String = "",
    val celular: String = "",
    val cedula: String = "",
    val tipoComprobante: Int = 0,
)