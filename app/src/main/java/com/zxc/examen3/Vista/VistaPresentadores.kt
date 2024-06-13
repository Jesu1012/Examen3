package com.zxc.examen3.Vista
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zxc.examen3.Controlador.ServicePresentadores


import com.zxc.examen3.Modelo.Presentadores
import com.zxc.examen3.Modelo.especialidadEnum
import com.zxc.examen3.Modelo.generoEnum
import com.zxc.examen3.R

@Composable
fun CrearPresentador() {
    var idPresentador by remember { mutableStateOf("") }
    var cuiPresentador by remember { mutableStateOf("") }
    var nombrePresentador by remember { mutableStateOf("") }
    var apellidoPaPresentador by remember { mutableStateOf("") }
    var apellidoMaPresentador by remember { mutableStateOf("") }
    var generoPresentador by remember { mutableStateOf(generoEnum.Otro) }
    var fechaNacimiento by remember { mutableStateOf("") }
    var fechaIngreso by remember { mutableStateOf("") }
    var direccionPresentador by remember { mutableStateOf("") }
    var telefonoPresentador by remember { mutableStateOf("") }
    var correoPresentador by remember { mutableStateOf("") }
    var especialidadPresentador by remember { mutableStateOf(especialidadEnum.Mago) }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text("Crear Presentadores", style = MaterialTheme.typography.headlineSmall)
        CampoFormulario("ID Presentador", idPresentador) { idPresentador = it }
        CampoFormulario("CUI Presentador", cuiPresentador) { cuiPresentador = it }
        CampoFormulario("Nombre", nombrePresentador) { nombrePresentador = it }
        CampoFormulario("Apellido Paterno", apellidoPaPresentador) { apellidoPaPresentador = it }
        CampoFormulario("Apellido Materno", apellidoMaPresentador) { apellidoMaPresentador = it }
        CampoSeleccionGenero("Género", generoPresentador) { generoPresentador = it }
        CampoFormulario("Fecha de Nacimiento", fechaNacimiento) { fechaNacimiento = it }
        CampoFormulario("Fecha de Ingreso", fechaIngreso) { fechaIngreso = it }
        CampoFormulario("Dirección", direccionPresentador) { direccionPresentador = it }
        CampoFormulario("Teléfono", telefonoPresentador) { telefonoPresentador = it }
        CampoFormulario("Correo Electrónico", correoPresentador) { correoPresentador = it }
        CampoSeleccionEspecialidad("Especialidad", especialidadPresentador) { especialidadPresentador = it }

        Button(onClick = {
            ServicePresentadores.agregarEventos(
                Presentadores(
                    idPresentador, cuiPresentador, nombrePresentador, apellidoPaPresentador,
                    apellidoMaPresentador, generoPresentador, fechaNacimiento, fechaIngreso,
                    telefonoPresentador, correoPresentador, direccionPresentador, especialidadPresentador
                )
            )

            idPresentador = ""
            cuiPresentador = ""
            nombrePresentador = ""
            apellidoPaPresentador = ""
            apellidoMaPresentador = ""
            generoPresentador = generoEnum.Otro
            fechaNacimiento = ""
            fechaIngreso = ""
            direccionPresentador = ""
            telefonoPresentador = ""
            correoPresentador = ""
            especialidadPresentador = especialidadEnum.Mago
        }) {
            Text("AGREGAR")
        }
    }
}

@Composable
fun ListarPresentador() {
    Text("Listar Presentadores", style = MaterialTheme.typography.headlineSmall)
    Column {
        ServicePresentadores.listarEventos().forEach { presentador ->
            Row(modifier = Modifier.padding(8.dp).background(Color.Magenta), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Person, contentDescription = "Presentador", tint = Color.White)
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("ID: ${presentador.idPresentador}")
                    Text("CUI: ${presentador.CUIPresentador}")
                    Text("Nombre: ${presentador.nombrePresentador} ${presentador.apellidoPaPresentador} ${presentador.apellidoMaPresentador}")
                    Text("Género: ${presentador.generoPresentador.descripcion}")
                    Text("Fecha Nacimiento: ${presentador.fechaNacimientoPresentador}")
                    Text("Dirección: ${presentador.direccionPresentador}")
                    Text("Teléfono: ${presentador.telefonoPresentador}")
                    Text("Correo: ${presentador.correoPresentador}")
                    Text("Especialidad: ${presentador.especialidadPresentado.descripcion}")
                }
                Spacer(Modifier.width(8.dp))
                Image(painter = painterResource(id = getImageForPresenter(presentador.especialidadPresentado)), contentDescription = "Presentador",
                    modifier = Modifier.size(150.dp))
            }
        }
    }
}

fun getImageForPresenter(especialidad: especialidadEnum): Int {
    return when (especialidad) {
        especialidadEnum.Mago -> R.drawable.v5
        especialidadEnum.Payaso -> R.drawable.v6
    }
}

