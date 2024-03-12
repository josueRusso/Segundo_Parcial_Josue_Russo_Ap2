package com.ucne.segundoparcial

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucne.segundoparcial.ui.Consulta.ConsultaScreen
import com.ucne.segundoparcial.ui.Registro.RegistroScreen
import com.ucne.segundoparcial.ui.theme.SegundoParcialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SegundoParcialTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "ClientesApp")
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        navController.navigate("Consulta")
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.List,
                                        contentDescription = "Consulta"
                                    )
                                }
                                IconButton(
                                        onClick = {
                                            navController.navigate("registro")
                                        }
                                        ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Agregar"
                                    )
                                }
                            }
                        )
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "Consulta",
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable("registro") {
                            RegistroScreen()
                        }
                        composable("Consulta") {
                            ConsultaScreen()
                        }
                    }
                }
            }
        }
    }
}

