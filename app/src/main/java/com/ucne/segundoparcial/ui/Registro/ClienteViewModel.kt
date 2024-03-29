package com.ucne.segundoparcial.ui.Registro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.segundoparcial.data.Repository.ClienteRepository
import com.ucne.segundoparcial.data.entity.ClienteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ClienteState())
    val state = _state.asStateFlow()
    val cliente: Flow<List<ClienteEntity>> = clienteRepository.getCliente()

    fun onEvent(event: ClienteEvent) {
        when (event) {
            is ClienteEvent.codigoCliente -> {
                _state.update {
                    it.copy(
                        cliente = it.cliente.copy(
                            codigoCliente = event.codigoCliente.toIntOrNull() ?: 0
                        )
                    )
                }
            }

            is ClienteEvent.nombre -> {
                _state.update {
                    it.copy(
                        cliente = it.cliente.copy(nombre = event.nombre)
                    )
                }
            }

            is ClienteEvent.direccion -> {
                _state.update {
                    it.copy(
                        cliente = it.cliente.copy(direccion = event.direccion)
                    )
                }
            }

            is ClienteEvent.telefono -> {
                _state.update {
                    it.copy(
                        cliente = it.cliente.copy(telefono = event.telefono)
                    )
                }
            }

            is ClienteEvent.celular -> {
                _state.update {
                    it.copy(
                        cliente = it.cliente.copy(celular = event.celular)
                    )
                }
            }

            is ClienteEvent.cedula -> {
                _state.update {
                    it.copy(
                        cliente = it.cliente.copy(cedula = event.cedula)
                    )
                }
            }

            is ClienteEvent.tipoComprobante -> {
                _state.update {
                    it.copy(
                        cliente = it.cliente.copy(
                            tipoComprobante = event.tipoComprobante.toIntOrNull() ?: 0
                        )
                    )
                }
            }

            ClienteEvent.onSave -> {
                val codigoCliente = state.value.cliente.codigoCliente
                val nombre = state.value.cliente.nombre
                val direccion = state.value.cliente.direccion
                val telefono = state.value.cliente.telefono
                val celular = state.value.cliente.celular
                val cedula = state.value.cliente.cedula
                val tipoComprobante = state.value.cliente.tipoComprobante

                if (nombre.isBlank() || direccion.isBlank() || telefono.isBlank() || celular.isBlank() ||
                    cedula.isBlank() || tipoComprobante == 0
                ) {
                    _state.update {
                        it.copy(
                            error = "Porfavor llene los campo"
                        )
                    }

                    val cliente = ClienteEntity(
                        codigoCliente = codigoCliente,
                        nombre = nombre,
                        direccion = direccion,
                        telefono = telefono,
                        celular = celular,
                        cedula = cedula,
                        tipoComprobante = tipoComprobante
                    )

                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            successMessage = null
                        )
                    }

                    viewModelScope.launch {
                        try {
                            clienteRepository.upsert(cliente)
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    successMessage = "Se guardo Correctamente"

                                )
                            }
                        } catch (e: Exception) {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    error = "Ocurrio un Error ${e.message}"
                                )
                            }
                        }
                    }
                    _state.update {
                        it.copy(
                            cliente = ClienteEntity()
                        )
                    }

                }
            }

            ClienteEvent.onNew -> {
                _state.update {
                    it.copy(
                        successMessage = null,
                        error = null,
                        cliente = ClienteEntity()
                    )
                }
            }

            is ClienteEvent.onDelete -> {
                viewModelScope.launch {
                    clienteRepository.delete(event.cliente)
                }
            }

        }
    }
}


data class ClienteState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val cliente: ClienteEntity = ClienteEntity(),
    val successMessage: String? = null
)

sealed interface ClienteEvent {
    data class codigoCliente(val codigoCliente: String) : ClienteEvent
    data class nombre(val nombre: String) : ClienteEvent
    data class direccion(val direccion: String) : ClienteEvent
    data class telefono(val telefono: String) : ClienteEvent
    data class celular(val celular: String) : ClienteEvent
    data class cedula(val cedula: String) : ClienteEvent
    data class tipoComprobante(val tipoComprobante: String) : ClienteEvent
    data class onDelete(val cliente: ClienteEntity) : ClienteEvent

    object onSave : ClienteEvent
    object onNew : ClienteEvent


}