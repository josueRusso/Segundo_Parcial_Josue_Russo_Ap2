package com.ucne.segundoparcial.ui.Registro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun RegistroScreen(
    viewModel: ClienteViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val cliente =state.cliente

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

        
    }

}