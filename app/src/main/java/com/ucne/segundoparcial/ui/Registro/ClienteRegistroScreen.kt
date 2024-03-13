package com.ucne.segundoparcial.ui.Registro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.delay

@Composable
fun RegistroScreen(
    viewModel: ClienteViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val cliente =state.cliente
    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())

        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Registro de Clientes",
            )

            OutlinedTextField(
                value = cliente.codigoCliente.toString(),
                onValueChange = { viewModel.onEvent(ClienteEvent.codigoCliente(it)) },
                label = { Text(text = "Id") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            OutlinedTextField(
                value = cliente.nombre,
                onValueChange = { viewModel.onEvent(ClienteEvent.nombre(it)) },
                label = { Text(text = "Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            OutlinedTextField(
                value = cliente.telefono,
                onValueChange = { viewModel.onEvent(ClienteEvent.telefono(it)) },
                label = { Text(text = "Telefono") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = cliente.direccion,
                onValueChange = { viewModel.onEvent(ClienteEvent.direccion(it)) },
                label = { Text(text = "Direccion") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            OutlinedTextField(
                value = cliente.celular,
                onValueChange = { viewModel.onEvent(ClienteEvent.celular(it)) },
                label = { Text(text = "Celular") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = cliente.cedula,
                onValueChange = { viewModel.onEvent(ClienteEvent.cedula(it)) },
                label = { Text(text = "Cedula") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = cliente.tipoComprobante.toString(),
                onValueChange = { viewModel.onEvent(ClienteEvent.tipoComprobante(it)) },
                label = { Text(text = "Tipo Comprobante") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(ClienteEvent.onSave)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Save")
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }

                Button(
                    onClick = {
                        viewModel.onEvent(ClienteEvent.onNew)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "New")
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }

            }
            state.successMessage?.let {
                MessageCard(message = it, color = Color.Green)
            }

            state.error?.let {
                MessageCard(message = it, color = Color.Red)
            }
        }
    }
}

@Composable
fun MessageCard(
    message: String,
    color: Color
) {
    var showMessage by remember { mutableStateOf(true) }
    
    LaunchedEffect(showMessage) {
        delay(1000)
        showMessage = false
    }

    if (showMessage) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(16.dp),
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = color
                ) {
                    Text(
                        text = message,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
