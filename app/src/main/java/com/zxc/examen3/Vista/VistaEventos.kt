package com.zxc.examen3.Vista

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zxc.examen3.Controlador.ServiceEventos

import com.zxc.examen3.Modelo.Eventos
import com.zxc.examen3.Modelo.tipoEventoEnum
import com.zxc.examen3.R

@Composable
fun CrearEvento() {
    var idEvento by remember { mutableStateOf("") }
    var tipoEvento by remember { mutableStateOf(tipoEventoEnum.Fiestas_de_cumpleaños) }
    var descripcion by remember { mutableStateOf("") }
    var duracionPre by remember { mutableStateOf("") }
    var precioBasePre by remember { mutableStateOf("") }
    var numAnimadoresPre by remember { mutableStateOf("") }
    var fechaEvento by remember { mutableStateOf("") }
    var ubicacionEvento by remember { mutableStateOf("") }

    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    val duracion = duracionPre.toIntOrNull()
    val precioBase = precioBasePre.toFloatOrNull()
    val numAnimadores = numAnimadoresPre.toIntOrNull()

    Column {
        Text("Crear Evento", style = MaterialTheme.typography.titleLarge)
        CampoFormulario("Id Evento", idEvento) { idEvento = it }
        CampoTipoEvento(tipoEvento) { tipoEvento = it }
        CampoFormulario("Descripción", descripcion) { descripcion = it }
        CampoFormulario("Duración (horas)", duracionPre) { duracionPre = it }
        CampoFormulario("Precio Base", precioBasePre) { precioBasePre = it }
        CampoFormulario("Número de Animadores", numAnimadoresPre) { numAnimadoresPre = it }
        CampoFormulario("Fecha del Evento", fechaEvento) { fechaEvento = it }
        CampoFormulario("Ubicación del Evento", ubicacionEvento) { ubicacionEvento = it }

        Button(onClick = {
            try {
                if (duracion == null || precioBase == null || numAnimadores == null) {
                    throw IllegalArgumentException("Asegúrate de que todos los campos numéricos esten correctamente llenados.")
                }
                ServiceEventos.agregarEventos(
                    Eventos(idEvento, tipoEvento, descripcion, duracion, precioBase, numAnimadores, fechaEvento, ubicacionEvento)
                )
                snackbarMessage = "Evento agregado correctamente"

                idEvento = ""
                descripcion = ""
                duracionPre = ""
                precioBasePre = ""
                numAnimadoresPre = ""
                fechaEvento = ""
                ubicacionEvento = ""
            } catch (e: Exception) {
                snackbarMessage = "Error al agregar evento: ${e.message}"
            }
        }) {
            Text("AGREGAR")
        }
    }

    if (snackbarMessage != null) {
        Snackbar(
            action = {
                Button(onClick = { snackbarMessage = null }) {
                    Text("OK")
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(snackbarMessage!!,Modifier.background(Color.Red))
        }
    }
}
@Composable
fun ListarEvento() {
    Text("Listar Evento", style = MaterialTheme.typography.headlineSmall)
    Column {
        ServiceEventos.listarEventos().forEach { evento ->
            Row(modifier = Modifier.padding(8.dp).background(Color.Black), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Star, contentDescription = "Evento", tint = Color.Blue)
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("ID: ${evento.idEventos}")
                    Text("Tipo: ${evento.tipoEvento.descripcion}")
                    Text("Descripción: ${evento.descripcion}")
                    Text("Duración: ${evento.duracion ?: "N/A"} horas")
                    Text("Precio Base: ${evento.precioBase ?: "N/A"}")
                    Text("Animadores: ${evento.numAnimadores ?: "N/A"}")
                    Text("Fecha: ${evento.FechaEvento}")
                    Text("Ubicación: ${evento.ubiEvento}")
                }
                Spacer(Modifier.width(8.dp))
                Image(painter = painterResource(id = getImageForEvent(evento.tipoEvento)), contentDescription = "Evento",
                    modifier = Modifier.size(150.dp))
            }
        }
    }
}

fun getImageForEvent(tipoEvento: tipoEventoEnum): Int {
    return when (tipoEvento) {
        tipoEventoEnum.Fiestas_de_cumpleaños -> R.drawable.v1
        tipoEventoEnum.Comunión -> R.drawable.v2
        tipoEventoEnum.Bautizo -> R.drawable.v3
        tipoEventoEnum.Eventos_de_empresa -> R.drawable.v4
    }
}

