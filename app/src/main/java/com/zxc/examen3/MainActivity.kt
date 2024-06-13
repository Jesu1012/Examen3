package com.zxc.examen3


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zxc.examen3.Controlador.ServiceClientes
import com.zxc.examen3.Controlador.ServiceEventos
import com.zxc.examen3.Controlador.ServicePresentadores
import com.zxc.examen3.Modelo.Clientes
import com.zxc.examen3.Modelo.Eventos
import com.zxc.examen3.Modelo.Presentadores
import com.zxc.examen3.Modelo.especialidadEnum
import com.zxc.examen3.Modelo.generoEnum
import com.zxc.examen3.Modelo.tipoEventoEnum
import com.zxc.examen3.ui.theme.Examen3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examen3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PanelPrincipal(Modifier.padding(innerPadding))
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Examen3Theme {
        PanelPrincipal()
    }
}
enum class PanelType{
    Panel1, Panel2, Panel3, Panel4
}
enum class currentCrudPanel{
    menu,crear, listar, actualizar, eliminar
}

@Composable
fun PanelPrincipal(modifier: Modifier = Modifier){
    var asd by remember { mutableStateOf(PanelType.Panel1) }
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(vertical = 16.dp)
        ) {

            Button(onClick = { asd = PanelType.Panel1 }) {
                Text("Gestion de Eventos")
            }
            Button(onClick = { asd = PanelType.Panel2 }) {
                Text("Gestion de Presentadores")
            }
            Button(onClick = { asd = PanelType.Panel3 }) {
                Text("Gestion de Clientes")
            }
            Button(onClick = { asd = PanelType.Panel4 }) {
                Text("Gestion de Contratos")
            }
        }
        when (asd) {
            PanelType.Panel1 -> centro(type =asd)
            PanelType.Panel2 -> centro(type =asd)
            PanelType.Panel3 -> centro(type =asd)
            PanelType.Panel4 -> centro(type =asd)
        }
    }


}



@Composable
fun centro(type: PanelType){
    var current by remember { mutableStateOf(currentCrudPanel.menu) }
    when(current) {
        currentCrudPanel.menu -> {
            Column {
                Button(onClick = { current = currentCrudPanel.crear }) {
                    Text("Crear")
                }
                Button(onClick = { current = currentCrudPanel.listar }) {
                    Text("Listar")
                }
                Button(onClick = { current = currentCrudPanel.actualizar }) {
                    Text("Actualizar")
                }
                Button(onClick = { current = currentCrudPanel.eliminar }) {
                    Text("Eliminar")
                }
            }
        }
        currentCrudPanel.crear -> Crear (type)
        currentCrudPanel.listar -> Listar(type)
        currentCrudPanel.actualizar -> Actualizar(type)
        currentCrudPanel.eliminar -> Eliminar(type)
    }
}

@Composable
fun Crear(type: PanelType) {
    when(type){
        PanelType.Panel1 -> {
            CrearEvento()
        }
        PanelType.Panel2 -> {
            CrearPresentador()
        }
        PanelType.Panel3 -> {
            CrearCliente()
        }
        PanelType.Panel4 -> {
            CrearContrato()
        }
    }
}
@Composable
fun Actualizar(type: PanelType) {
    when(type){
        PanelType.Panel1 -> {
            ActualizarEvento()
        }
        PanelType.Panel2 -> {
            ActualizarPresentador()
        }
        PanelType.Panel3 -> {
            ActualizarCliente()
        }
        PanelType.Panel4 -> {
            ActualizarContrato()
        }
    }
}
@Composable
fun Eliminar(type: PanelType) {
    when(type){
        PanelType.Panel1 -> {
            EliminarEvento()
        }
        PanelType.Panel2 -> {
            EliminarPresentador()
        }
        PanelType.Panel3 -> {
            EliminarCliente()
        }
        PanelType.Panel4 -> {
            EliminarContrato()
        }
    }
}
@Composable
fun Listar(type: PanelType) {
    when(type){
        PanelType.Panel1 -> {
            ListarEvento()
        }
        PanelType.Panel2 -> {
            ListarPresentador()
        }
        PanelType.Panel3 -> {
            ListarCliente()
        }
        PanelType.Panel4 -> {
            ListarContrato()
        }
    }
}

@Composable
fun CrearEvento(){
    var responsev1 by remember { mutableStateOf("") }

    var responsev2 by remember { mutableStateOf(tipoEventoEnum.Fiestas_de_cumpleaños) }
    var responsev3 by remember { mutableStateOf("") }
    var responsev4pre by remember { mutableStateOf("") }
    var responsev4 = responsev4pre.toIntOrNull()
    var responsev5pre by remember { mutableStateOf("") }
    var responsev5 = responsev5pre.toFloatOrNull()
    var responsev6pre by remember { mutableStateOf("") }
    var responsev6 = responsev6pre.toIntOrNull()
    var responsev7 by remember { mutableStateOf("") }
    var responsev8 by remember { mutableStateOf("") }
    Column {
        Text("Crear Evento")
        Row {
            Text(text = "Id Evento")
            TextField(value = responsev1, onValueChange = {
                responsev1 = it
            })
        }
        Row {
            Text(text = "Tipo de Evento")

            var selectedTheme by remember { mutableStateOf(tipoEventoEnum.Fiestas_de_cumpleaños) }

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(vertical = 16.dp)
            ) {
                tipoEventoEnum.values().forEachIndexed { index, theme ->
                    ThemeSelectionItem(
                        theme = theme.descripcion,
                        color = Color.Magenta,
                        isChecked = selectedTheme == theme,
                        onCheckedChange = { isChecked ->
                            selectedTheme = if (isChecked) theme else tipoEventoEnum.Fiestas_de_cumpleaños
                            responsev2 = selectedTheme
                        }
                    )
                }
            }

        }
        Row {
            Text(text = "Descripcion")
            TextField(value = responsev3, onValueChange = {
                responsev3 = it
            })
        }
        Row {
            Text(text = "Duracion")
            TextField(value = responsev4pre, onValueChange = {
                responsev4pre = it
            })
        }
        Row {
            Text(text = "Precio Base")
            TextField(value = responsev5pre, onValueChange = {
                responsev5pre = it
            })
        }
        Row {
            Text(text = "Numero de animadores necesarios")
            TextField(value = responsev6pre, onValueChange = {
                responsev6pre = it
            })
        }
        Row {
            Text(text = "Fecha de evento")
            TextField(value = responsev7, onValueChange = {
                responsev7 = it
            })
        }
        Row {
            Text(text = "Ubicacion del evento")
            TextField(value = responsev8, onValueChange = {
                responsev8 = it
            })
        }
        Row {
            Button(onClick = {
                ServiceEventos.agregarEventos(
                    Eventos(
                        responsev1,
                        responsev2,
                        responsev3,
                        responsev4!!,
                        responsev5!!,
                        responsev6!!,
                        responsev7,
                        responsev8
                    )
                )
                responsev1 = ""
                responsev2 = tipoEventoEnum.Fiestas_de_cumpleaños
                responsev3 = ""
                responsev4pre = ""
                responsev5pre = ""
                responsev6pre = ""
                responsev7 = ""
                responsev8 = ""


            }) {
                Text(text = "AGREGAR")
            }
        }
    }

    
}
@Composable
    fun ThemeSelectionItem(theme: String, color: Color, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = theme.toString(), // Tomar las primeras dos letras como iniciales
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun CrearPresentador(){
    var responsev1 by remember { mutableStateOf("") }
    var responsev2 by remember { mutableStateOf("") }
    var responsev3 by remember { mutableStateOf("") }
    var responsev4 by remember { mutableStateOf("") }
    var responsev5 by remember { mutableStateOf("") }
    var responsev6 by remember { mutableStateOf(generoEnum.Otro) }
    var responsev7 by remember { mutableStateOf("") }
    var responsev8 by remember { mutableStateOf("") }
    var responsev9 by remember { mutableStateOf("") }
    var responsev10 by remember { mutableStateOf("") }
    var responsev11 by remember { mutableStateOf("") }
    var responsev12 by remember { mutableStateOf(especialidadEnum.Mago) }
    Column (Modifier.verticalScroll(rememberScrollState())){
        Text("Crear Presentadores")
        //1
        Row {
            Text(text = "Id Presentador")
            TextField(value = responsev1, onValueChange = {
                responsev1 = it
            })
        }
        //2
        Row {
            Text(text = "CUIPresentador")
            TextField(value = responsev2, onValueChange = {
                responsev2 = it
            })
        }
        //3
        Row {
            Text(text = "nombrePresentador")
            TextField(value = responsev3, onValueChange = {
                responsev3 = it
            })
        }
        //4
        Row {
            Text(text = "apellidoPaPresentador")
            TextField(value = responsev4, onValueChange = {
                responsev4 = it
            })
        }
        //5
        Row {
            Text(text = "apellidoMaPresentador")
            TextField(value = responsev5, onValueChange = {
                responsev5 = it
            })
        }
        //6
        Row {
            Text(text = "generoPresentador")

            var selectedTheme by remember { mutableStateOf(generoEnum.Otro) }

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(vertical = 16.dp)
            ) {
                generoEnum.values().forEachIndexed { index, theme ->
                    ThemeSelectionItem(
                        theme = theme.descripcion,
                        color = Color.Magenta,
                        isChecked = selectedTheme == theme,
                        onCheckedChange = { isChecked ->
                            selectedTheme = if (isChecked) theme else generoEnum.Otro
                            responsev6 = selectedTheme
                        }
                    )
                }
            }

        }
        //7
        Row {
            Text(text = "apellidoMaPresentador")
            TextField(value = responsev7, onValueChange = {
                responsev7 = it
            })
        }
        //8
        Row {
            Text(text = "apellidoMaPresentador")
            TextField(value = responsev8, onValueChange = {
                responsev8 = it
            })
        }
        //9
        Row {
            Text(text = "apellidoMaPresentador")
            TextField(value = responsev9, onValueChange = {
                responsev9 = it
            })
        }
        //10
        Row {
            Text(text = "Descripcion")
            TextField(value = responsev10, onValueChange = {
                responsev10 = it
            })
        }
        //11
        Row {
            Text(text = "Duracion")
            TextField(value = responsev11, onValueChange = {
                responsev11 = it
            })
        }
        //12
        Row {
            Text(text = "Especialidad")

            var selectedTheme by remember { mutableStateOf(especialidadEnum.Mago) }

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(vertical = 16.dp)
            ) {
                especialidadEnum.values().forEachIndexed { index, theme ->
                    ThemeSelectionItem(
                        theme = theme.descripcion,
                        color = Color.Magenta,
                        isChecked = selectedTheme == theme,
                        onCheckedChange = { isChecked ->
                            selectedTheme = if (isChecked) theme else especialidadEnum.Mago
                            responsev12 = selectedTheme
                        }
                    )
                }
            }

        }

        Row {
            Button(onClick = {
                ServicePresentadores.agregarEventos(
                    Presentadores(
                        responsev1,
                        responsev2,
                        responsev3,
                        responsev4,
                        responsev5,
                        responsev6,
                        responsev7,
                        responsev8,
                        responsev9,
                        responsev10,
                        responsev11,
                        responsev12
                    )
                )
                responsev1 = ""
                responsev2 = ""
                responsev3 = ""
                responsev4 = ""
                responsev5 = ""
                responsev6 = generoEnum.Otro
                responsev7 = ""
                responsev8 = ""
                responsev9 = ""
                responsev10 = ""
                responsev11 = ""
                responsev12 = especialidadEnum.Mago


            }) {
                Text(text = "AGREGAR")
            }
        }
    }

}

@Composable
fun CrearCliente(){
    var responsev1 by remember { mutableStateOf("") }
    var responsev2 by remember { mutableStateOf("") }
    var responsev3 by remember { mutableStateOf("") }
    var responsev4 by remember { mutableStateOf("") }
    var responsev5 by remember { mutableStateOf("") }
    var responsev6 by remember { mutableStateOf("") }
    var responsev7 by remember { mutableStateOf("") }
    var responsev8 by remember { mutableStateOf("") }

    Column {
        Text("Crear Cliente")
        //1
        Row {
            Text(text = "idCliente")
            TextField(value = responsev1, onValueChange = {
                responsev1 = it
            })
        }
        //2
        Row {
            Text(text = "idCliente")
            TextField(value = responsev2, onValueChange = {
                responsev2 = it
            })
        }
        //3
        Row {
            Text(text = "idCliente")
            TextField(value = responsev3, onValueChange = {
                responsev3 = it
            })
        }
        //4
        Row {
            Text(text = "idCliente")
            TextField(value = responsev4, onValueChange = {
                responsev4 = it
            })
        }
        //5
        Row {
            Text(text = "idCliente")
            TextField(value = responsev5, onValueChange = {
                responsev5 = it
            })
        }
        //6
        Row {
            Text(text = "idCliente")
            TextField(value = responsev6, onValueChange = {
                responsev6 = it
            })
        }
        //7
        Row {
            Text(text = "idCliente")
            TextField(value = responsev7, onValueChange = {
                responsev7 = it
            })
        }
        //8
        Row {
            Text(text = "idCliente")
            TextField(value = responsev8, onValueChange = {
                responsev8 = it
            })
        }

        Row {
            Button(onClick = {
                ServiceClientes.agregarEventos(
                    Clientes(
                        responsev1,
                        responsev2,
                        responsev3,
                        responsev4,
                        responsev5,
                        responsev6,
                        responsev7,
                        responsev8
                    )
                )

            }) {
                Text(text = "AGREGAR")
            }
        }
    }


}
@Composable
fun CrearContrato(){
    Text("Crear Contrato")
}
//
@Composable
fun ActualizarEvento(){
    Text("Actualizar Evento")
}
@Composable
fun ActualizarPresentador(){
    Text("Actualizar Presentador")
}

@Composable
fun ActualizarCliente(){
    Text("ActualizarCliente")
}
@Composable
fun ActualizarContrato(){
    Text("Actualizar Contrato")
}
//
@Composable
fun EliminarEvento(){
    Text("EliminarEvento")
}
@Composable
fun EliminarPresentador(){
    Text("Eliminar Presentador")
}

@Composable
fun EliminarCliente(){
    Text("Eliminar Cliente")
}
@Composable
fun EliminarContrato(){
    Text("Eliminar Contrato")
}
//
@Composable
fun ListarEvento(){
    Text("Listar Evento")
    Column {
        ServiceEventos.listarEventos().forEach {
            Column (Modifier.background(Color.Yellow)){
                Text(it.idEventos.toString())
                Text(it.tipoEvento.toString())
                Text(it.descripcion)
                Text(it.duracion.toString())
                Text(it.precioBase.toString())
                Text(it.numAnimadores.toString())
                Text(it.FechaEvento.toString())
                Text(it.ubiEvento)
            }
            Spacer(modifier = Modifier.size(10.dp))
        }

    }
}
@Composable
fun ListarPresentador(){
    Text("Listar Evento")
    Column {
        ServicePresentadores.listarEventos().forEach {
            Column (Modifier.background(Color.Yellow)){
                Text(it.idPresentador)
                Text(it.CUIPresentador)
                Text(it.nombrePresentador)
                Text(it.apellidoPaPresentador)
                Text(it.apellidoMaPresentador)
                Text(it.generoPresentador.toString())
                Text(it.fechaNacimientoPresentador)
                Text(it.direccionPresentador)
                Text(it.telefonoPresentador)
                Text(it.correoPresentador)
                Text(it.direccionPresentador)
                Text(it.especialidadPresentado.toString())
            }
            Spacer(modifier = Modifier.size(10.dp))
        }

    }
}

@Composable
fun ListarCliente(){
    Text("Listar Cliente")
}
@Composable
fun ListarContrato(){
    Text("Listar Contrato")
}
//

