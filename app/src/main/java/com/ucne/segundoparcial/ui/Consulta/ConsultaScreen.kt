package com.ucne.segundoparcial.ui.Consulta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.segundoparcial.data.entity.ClienteEntity
import com.ucne.segundoparcial.ui.Registro.ClienteEvent
import com.ucne.segundoparcial.ui.Registro.ClienteViewModel

@Composable
fun ConsultaScreen(
    viewModel: ClienteViewModel = hiltViewModel()
) {
    val cliente by viewModel.cliente.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "Consulta de Cliente",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        LazyColumn{
            items(cliente) { cliente ->
                Card(cliente = cliente, onDeleteClick = { viewModel.onEvent(ClienteEvent.onDelete(cliente)) })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun Card(
    cliente: ClienteEntity,
    onDeleteClick: () -> Unit

) {
    var showDialog by remember { mutableStateOf(false) }

    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("ID: ")
                            }
                            withStyle(style = SpanStyle()) {
                                append("${cliente.codigoCliente}")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Nombre: ")
                            }
                            withStyle(style = SpanStyle()) {
                                append(cliente.nombre)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Direccion: ")
                            }
                            withStyle(style = SpanStyle()) {
                                append(cliente.direccion)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Telefono: ")
                            }
                            withStyle(style = SpanStyle()) {
                                append(cliente.telefono)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Celular: ")
                            }
                            withStyle(style = SpanStyle()) {
                                append(cliente.celular)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Cedula: ")
                            }
                            withStyle(style = SpanStyle()) {
                                append(cliente.cedula)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("tipo Comprobante: ")
                            }
                            withStyle(style = SpanStyle()) {
                                append("${cliente.tipoComprobante}")
                            }
                        }
                    )

                }
                IconButton(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar"
                    )
                }
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        icon = { Icon(Icons.Default.Warning, contentDescription = null) },
                        text = {
                            Text("¿Estás seguro de eliminar este cliente?")
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    onDeleteClick()
                                    showDialog = false
                                }
                            ) {
                                Text("Eliminar")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    showDialog = false
                                }
                            ) {
                                Text("Cancelar")
                            }
                        }
                    )
                }
            }
        }
    }
}
