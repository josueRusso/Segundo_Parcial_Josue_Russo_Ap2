package com.ucne.segundoparcial.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("cliente")
data class ClienteEntity(
    @PrimaryKey(autoGenerate = true)
    val codigoCliente: Int = 0,
    val nombre: String = "",
    val direccion: String = "",
    val telefono: String = "",
    val celular: String = "",
    val cedula: String = "",
    val tipoComprobante: Int = 0,
)
