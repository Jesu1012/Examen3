package com.zxc.examen3


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zxc.examen3.Controlador.ServiceClientes
import com.zxc.examen3.Controlador.ServiceContratos
import com.zxc.examen3.Controlador.ServiceEventos
import com.zxc.examen3.Controlador.ServicePresentadores
import com.zxc.examen3.Modelo.Clientes
import com.zxc.examen3.Modelo.Contratos
import com.zxc.examen3.Modelo.Eventos
import com.zxc.examen3.Modelo.Presentadores
import com.zxc.examen3.Modelo.especialidadEnum
import com.zxc.examen3.Modelo.generoEnum
import com.zxc.examen3.Modelo.tipoEventoEnum
import com.zxc.examen3.Vista.CrearCliente
import com.zxc.examen3.Vista.CrearEvento
import com.zxc.examen3.Vista.CrearPresentador
import com.zxc.examen3.Vista.ListarEvento
import com.zxc.examen3.Vista.ListarPresentador
import com.zxc.examen3.ui.theme.Examen3Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    Panel_Eventos, Panel_Presentadores, Panel_Clientes, Panel_Contratos
}
enum class currentCrudPanel{
    menu,crear, listar, actualizar, eliminar
}
var snackbarJob: Job? = null

@Composable
fun PanelPrincipal(modifier: Modifier = Modifier) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var currentPanel by remember { mutableStateOf(PanelType.Panel_Eventos) }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(vertical = 16.dp)
        ) {
            Button(onClick = {
                LanzarCoroutina(PanelType.Panel_Eventos, scope, snackbarHostState, 500)
                currentPanel = PanelType.Panel_Eventos
            }) {
                Text("Gestión de Eventos")
            }
            Button(onClick = {
                LanzarCoroutina(PanelType.Panel_Presentadores, scope, snackbarHostState, 500)
                currentPanel = PanelType.Panel_Presentadores
            }) {
                Text("Gestión de Presentadores")
            }
            Button(onClick = {
                LanzarCoroutina(PanelType.Panel_Clientes, scope, snackbarHostState, 500)
                currentPanel = PanelType.Panel_Clientes
            }) {
                Text("Gestión de Clientes")
            }
            Button(onClick = {
                LanzarCoroutina(PanelType.Panel_Contratos, scope, snackbarHostState, 500)
                currentPanel = PanelType.Panel_Contratos
            }) {
                Text("Gestión de Contratos")
            }
        }
        SnackbarHost(hostState = snackbarHostState)
        when (currentPanel) {
            PanelType.Panel_Eventos -> centro(type = currentPanel)
            PanelType.Panel_Presentadores -> centro(type = currentPanel)
            PanelType.Panel_Clientes -> centro(type = currentPanel)
            PanelType.Panel_Contratos -> centro(type = currentPanel)
        }


    }
}

fun LanzarCoroutina(panelType: PanelType, scope: CoroutineScope, snackbarHostState: SnackbarHostState, durationMs: Long) {
    val message = when (panelType) {
        PanelType.Panel_Eventos -> "Gestión de Eventos"
        PanelType.Panel_Presentadores -> "Gestión de Presentadores"
        PanelType.Panel_Clientes -> "Gestión de Clientes"
        PanelType.Panel_Contratos -> "Gestión de Contratos"
    }


    snackbarJob?.cancel()
    snackbarJob = scope.launch {

        snackbarHostState.showSnackbar(
            message = "Cambiado a $message",
            duration = when {
                durationMs <= 1500 -> SnackbarDuration.Short
                else -> SnackbarDuration.Long
            }
        )

    }
}

@Composable
fun centro(type: PanelType){
    var current by remember { mutableStateOf(currentCrudPanel.menu) }
    when(current) {
        currentCrudPanel.menu -> {
            Column {
                Text("Menu Principal ${type}")
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
        currentCrudPanel.crear -> {
            Crear(type, onBack = { current = currentCrudPanel.menu })
        }
        currentCrudPanel.listar -> {
            Listar(type, onBack = { current = currentCrudPanel.menu })
        }
        currentCrudPanel.actualizar -> {
            Actualizar(type, onBack = { current = currentCrudPanel.menu })
        }
        currentCrudPanel.eliminar -> {
            Eliminar(type, onBack = { current = currentCrudPanel.menu })
        }
    }
}

@Composable
fun Crear(type: PanelType, onBack: () -> Unit) {
    Column {
        when(type){
            PanelType.Panel_Eventos -> CrearEvento()
            PanelType.Panel_Presentadores -> CrearPresentador()
            PanelType.Panel_Clientes -> CrearCliente()
            PanelType.Panel_Contratos -> CrearContrato()
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = onBack) {
            Text("Regresar al Menú Principal")
        }
    }
}
@Composable
fun Actualizar(type: PanelType, onBack: () -> Unit) {
    when(type){
        PanelType.Panel_Eventos -> {
            ActualizarEvento()
        }
        PanelType.Panel_Presentadores -> {
            ActualizarPresentador()
        }
        PanelType.Panel_Clientes -> {
            ActualizarCliente()
        }
        PanelType.Panel_Contratos -> {
            ActualizarContrato()
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Button(onClick = onBack) {
        Text("Regresar al Menú Principal")
    }
}
@Composable
fun Eliminar(type: PanelType, onBack: () -> Unit) {
    when(type){
        PanelType.Panel_Eventos -> {
            EliminarEvento()
        }
        PanelType.Panel_Presentadores -> {
            EliminarPresentador()
        }
        PanelType.Panel_Clientes -> {
            EliminarCliente()
        }
        PanelType.Panel_Contratos -> {
            EliminarContrato()
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Button(onClick = onBack) {
        Text("Regresar al Menú Principal")
    }
}
@Composable
fun Listar(type: PanelType, onBack: () -> Unit) {
    when(type){
        PanelType.Panel_Eventos -> {
            ListarEvento()
        }
        PanelType.Panel_Presentadores -> {
            ListarPresentador()
        }
        PanelType.Panel_Clientes -> {
            ListarCliente()
        }
        PanelType.Panel_Contratos -> {
            ListarContrato()
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Button(onClick = onBack) {
        Text("Regresar al Menú Principal")
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
fun EliminarEvento() {
    val eventos = ServiceEventos.listarEventos()
    var selectedEvento by remember { mutableStateOf<Eventos?>(null) }
    val showDialog = remember { mutableStateOf(false) }

    if (eventos.isEmpty()) {
        Text("No hay eventos para eliminar.")
    } else {
        Column {
            DropdownMenuEventos(eventos, selectedEvento) { selectedEvento = it }
            Button(
                onClick = { showDialog.value = true },
                enabled = selectedEvento != null
            ) {
                Text("Eliminar Evento")
            }
            if (showDialog.value) {
                ConfirmarEliminacionDialog(
                    "¿Está seguro de que desea eliminar este evento?",
                    onConfirm = {
                        selectedEvento?.let { ServiceEventos.eliminarEventos(it.idEventos) }
                        showDialog.value = false
                    },
                    onDismiss = { showDialog.value = false }
                )
            }
        }
    }
}

@Composable
fun EliminarPresentador() {
    val presentadores = ServicePresentadores.listarEventos()
    var selectedPresentador by remember { mutableStateOf<Presentadores?>(null) }
    val showDialog = remember { mutableStateOf(false) }

    if (presentadores.isEmpty()) {
        Text("No hay presentadores para eliminar.")
    } else {
        Column {
            DropdownMenuPresentadores(presentadores, selectedPresentador) { selectedPresentador = it }
            Button(
                onClick = { showDialog.value = true },
                enabled = selectedPresentador != null
            ) {
                Text("Eliminar Presentador")
            }
            if (showDialog.value) {
                ConfirmarEliminacionDialog(
                    "¿Está seguro de que desea eliminar este presentador?",
                    onConfirm = {
                        selectedPresentador?.let { ServicePresentadores.eliminarEventos(it.idPresentador) }
                        showDialog.value = false
                    },
                    onDismiss = { showDialog.value = false }
                )
            }
        }
    }
}

@Composable
fun EliminarCliente() {
    val clientes = ServiceClientes.listarEventos()
    var selectedCliente by remember { mutableStateOf<Clientes?>(null) }
    val showDialog = remember { mutableStateOf(false) }

    if (clientes.isEmpty()) {
        Text("No hay clientes para eliminar.")
    } else {
        Column {
            DropdownMenuClientes(clientes, selectedCliente) { selectedCliente = it }
            Button(
                onClick = { showDialog.value = true },
                enabled = selectedCliente != null
            ) {
                Text("Eliminar Cliente")
            }
            if (showDialog.value) {
                ConfirmarEliminacionDialog(
                    "¿Está seguro de que desea eliminar este cliente?",
                    onConfirm = {
                        selectedCliente?.let { ServiceClientes.eliminarEventos(it.idCliente) }
                        showDialog.value = false
                    },
                    onDismiss = { showDialog.value = false }
                )
            }
        }
    }
}

@Composable
fun EliminarContrato() {
    val contratos = ServiceContratos.listarEventos()
    var selectedContrato by remember { mutableStateOf<Contratos?>(null) }
    val showDialog = remember { mutableStateOf(false) }

    if (contratos.isEmpty()) {
        Text("No hay contratos para eliminar.")
    } else {
        Column {
            DropdownMenuContratos(contratos, selectedContrato) { selectedContrato = it }
            Button(
                onClick = { showDialog.value = true },
                enabled = selectedContrato != null
            ) {
                Text("Eliminar Contrato")
            }
            if (showDialog.value) {
                ConfirmarEliminacionDialog(
                    "¿Está seguro de que desea eliminar este contrato?",
                    onConfirm = {
                        selectedContrato?.let { ServiceContratos.eliminarEventos(it.idContratos) }
                        showDialog.value = false
                    },
                    onDismiss = { showDialog.value = false }
                )
            }
        }
    }
}

@Composable
fun ConfirmarEliminacionDialog(mensaje: String, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onConfirm) { Text("Confirmar") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancelar") }
        },
        text = { Text(mensaje) }
    )
}
@Composable
fun DropdownMenuClientes(clientes: List<Clientes>, selectedCliente: Clientes?, onClienteSelected: (Clientes) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = selectedCliente?.nombrCliente ?: "Seleccione un cliente",
            modifier = Modifier.clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            clientes.forEach { cliente ->
                DropdownMenuItem(onClick = {
                    onClienteSelected(cliente)
                    expanded = false
                }, text = { Text("${cliente.nombrCliente} - ${cliente.idCliente}") })
            }
        }
    }
}

@Composable
fun DropdownMenuContratos(contratos: List<Contratos>, selectedContrato: Contratos?, onContratoSelected: (Contratos) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = selectedContrato?.idContratos ?: "Seleccione un contrato",
            modifier = Modifier.clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            contratos.forEach { contrato ->
                DropdownMenuItem(onClick = {
                    onContratoSelected(contrato)
                    expanded = false
                }, text = { Text("Contrato ${contrato.idContratos} - ${contrato.ClienteRegistrado?.nombrCliente ?: "Desconocido"}") })
            }
        }
    }
}

@Composable
fun DropdownMenuPresentadores(
    presentadores: List<Presentadores>,
    selectedPresentador: Presentadores?,
    onPresentadorSelected: (Presentadores) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = selectedPresentador?.let { "${it.nombrePresentador} ${it.apellidoPaPresentador}" } ?: "Seleccione un presentador",
            modifier = Modifier
                .clickable { expanded = true }
                .padding(8.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            presentadores.forEach { presentador ->
                DropdownMenuItem(onClick = {
                    onPresentadorSelected(presentador)
                    expanded = false
                }, text = { Text("${presentador.nombrePresentador} ${presentador.apellidoPaPresentador} - ${presentador.idPresentador}") })
            }
        }
    }
}

@Composable
fun DropdownMenuEventos(
    eventos: List<Eventos>,
    selectedEvento: Eventos?,
    onEventoSelected: (Eventos) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = selectedEvento?.let { "${it.tipoEvento.descripcion} - ${it.idEventos}" } ?: "Seleccione un evento",
            modifier = Modifier
                .clickable { expanded = true }
                .padding(8.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            eventos.forEach { evento ->
                DropdownMenuItem(onClick = {
                    onEventoSelected(evento)
                    expanded = false
                }, text = { Text("${evento.tipoEvento.descripcion} - ${evento.idEventos}") })
            }
        }
    }
}

//



@Composable
fun ListarCliente() {
    Text("Listar Clientes", style = MaterialTheme.typography.headlineSmall)
    Column {
        ServiceClientes.listarEventos().forEach { cliente ->
            Row(modifier = Modifier.padding(8.dp).background(Color.Black), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.AccountBox, contentDescription = "Cliente", tint = Color.Green)
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("ID: ${cliente.idCliente}")
                    Text("Nombre: ${cliente.nombrCliente}")
                    Text("Apellido Paterno: ${cliente.apellidoPaCliente}")
                    Text("Apellido Materno: ${cliente.apellidoMaCliente}")
                    Text("Domicilio: ${cliente.domicilioCliente}")
                    Text("Teléfono: ${cliente.telefonoCliente}")
                    Text("Correo: ${cliente.correoCliente}")
                    Text("Fecha de Registro: ${cliente.fechaRegistroCliente}")
                }
            }
        }
    }
}

@Composable
fun ListarContrato() {
    Text("Listar Contratos", style = MaterialTheme.typography.headlineSmall)
    Column {
        ServiceContratos.listarEventos().forEach { contrato ->
            Row(modifier = Modifier.padding(8.dp).background(Color.Black), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Email, contentDescription = "Contrato", tint = Color.Blue)
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("ID Contrato: ${contrato.idContratos}")
                    Text("Evento: ${contrato.EventoRegistrado?.idEventos ?: "N/A"}")
                    Text("Presentador: ${contrato.PresentadorRegistrado?.idPresentador ?: "N/A"}")
                    Text("Cliente: ${contrato.ClienteRegistrado?.idCliente ?: "N/A"}")
                    Text("Lugar del Evento: ${contrato.lugarEvento}")
                    Text("Fecha del Contrato: ${contrato.fechaContrato}")
                    Text("Forma de Pago: ${contrato.formaPago.descripcion}")
                    Text("Precio Final: ${contrato.precioFinalAcordado}")
                    Text("Descuento Aplicado: ${contrato.descuentoAplicado}")
                    Text("Observaciones: ${contrato.observaciones}")
                    Text("Estado de la Compra: ${contrato.estadoCompra.descripcion}")
                }
            }
        }
    }
}

//

