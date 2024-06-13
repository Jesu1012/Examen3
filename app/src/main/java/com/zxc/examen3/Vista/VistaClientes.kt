package com.zxc.examen3.Vista
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zxc.examen3.Controlador.ServiceClientes
import com.zxc.examen3.Controlador.ServiceEventos
import com.zxc.examen3.Controlador.ServicePresentadores
import com.zxc.examen3.Modelo.Clientes


import com.zxc.examen3.Modelo.Presentadores
import com.zxc.examen3.Modelo.especialidadEnum
import com.zxc.examen3.Modelo.generoEnum
@Composable
fun CrearCliente() {
    var idCliente by remember { mutableStateOf("") }
    var nombreCliente by remember { mutableStateOf("") }
    var apellidoPaCliente by remember { mutableStateOf("") }
    var apellidoMaCliente by remember { mutableStateOf("") }
    var domicilioCliente by remember { mutableStateOf("") }
    var telefonoCliente by remember { mutableStateOf("") }
    var correoCliente by remember { mutableStateOf("") }
    var fechaRegistroCliente by remember { mutableStateOf("") }

    Column {
        Text("Crear Cliente", style = MaterialTheme.typography.headlineSmall)
        CampoFormulario("ID Cliente", idCliente) { idCliente = it }
        CampoFormulario("Nombre", nombreCliente) { nombreCliente = it }
        CampoFormulario("Apellido Paterno", apellidoPaCliente) { apellidoPaCliente = it }
        CampoFormulario("Apellido Materno", apellidoMaCliente) { apellidoMaCliente = it }
        CampoFormulario("Domicilio", domicilioCliente) { domicilioCliente = it }
        CampoFormulario("Teléfono", telefonoCliente) { telefonoCliente = it }
        CampoFormulario("Correo Electrónico", correoCliente) { correoCliente = it }
        CampoFormulario("Fecha de Registro", fechaRegistroCliente) { fechaRegistroCliente = it }

        Button(onClick = {
            ServiceClientes.agregarEventos(
                Clientes(
                    idCliente, nombreCliente, apellidoPaCliente, apellidoMaCliente,
                    domicilioCliente, telefonoCliente, correoCliente, fechaRegistroCliente
                )
            )

            idCliente = ""
            nombreCliente = ""
            apellidoPaCliente = ""
            apellidoMaCliente = ""
            domicilioCliente = ""
            telefonoCliente = ""
            correoCliente = ""
            fechaRegistroCliente = ""
        }) {
            Text("AGREGAR")
        }
    }
}