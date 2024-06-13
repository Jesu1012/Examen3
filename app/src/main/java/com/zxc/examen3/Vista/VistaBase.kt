package com.zxc.examen3.Vista

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.zxc.examen3.Modelo.especialidadEnum
import com.zxc.examen3.Modelo.generoEnum
import com.zxc.examen3.Modelo.tipoEventoEnum

@Composable
fun CampoFormulario(etiqueta: String, valor: String, enCambioDeValor: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("$etiqueta:", modifier = Modifier.width(150.dp))
        TextField(
            value = valor,
            onValueChange = enCambioDeValor,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CampoTipoEvento(temaSeleccionado: tipoEventoEnum, enSeleccionDeTema: (tipoEventoEnum) -> Unit) {
    Text("Tipo de Evento:")
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        tipoEventoEnum.values().forEach { tema ->
            ElementoSeleccionTema(
                tema = tema.descripcion,
                color = Color.Magenta,
                isChecked = temaSeleccionado == tema,
                onCheckedChange = { if (it) enSeleccionDeTema(tema) }
            )
        }
    }
}


@Composable
fun CampoSeleccionGenero(etiqueta: String, generoActual: generoEnum, enCambioDeGenero: (generoEnum) -> Unit) {
    Text("$etiqueta:")
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        generoEnum.values().forEach { genero ->
            ElementoSeleccionTema(
                tema = genero.descripcion,
                color = Color.Magenta,
                isChecked = generoActual == genero,
                onCheckedChange = { if (it) enCambioDeGenero(genero) }
            )
        }
    }
}

@Composable
fun CampoSeleccionEspecialidad(etiqueta: String, especialidadActual: especialidadEnum, enCambioDeEspecialidad: (especialidadEnum) -> Unit) {
    Text("$etiqueta:")
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        especialidadEnum.values().forEach { especialidad ->
            ElementoSeleccionTema(
                tema = especialidad.descripcion,
                color = Color.Magenta,
                isChecked = especialidadActual == especialidad,
                onCheckedChange = { if (it) enCambioDeEspecialidad(especialidad) }
            )
        }
    }
}

@Composable
fun ElementoSeleccionTema(tema: String, color: Color, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(120.dp)
                .height(45.dp)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = tema,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}
