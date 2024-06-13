package com.zxc.examen3.Controlador

import android.util.Log
import com.zxc.examen3.Modelo.Clientes
import com.zxc.examen3.Modelo.Contratos
import com.zxc.examen3.Modelo.Eventos
import com.zxc.examen3.Modelo.Presentadores

object ServiceEventos {
    private val listEventos = mutableListOf<Eventos>()

    fun agregarEventos(eventos: Eventos) {
        listEventos.add(eventos)

    }

    fun obtenerEventosPorId(id: String): Eventos? = listEventos.find { it.idEventos == id }

    fun actualizarEventos(id: String, eventos: Eventos){
        for(i in 0 until listEventos.size){
            if(listEventos[i].idEventos == id){
                listEventos[i] = eventos
            }
        }

    }
    fun eliminarEventos(id: String) {
        listEventos.removeIf{it.idEventos == id}
    }
    fun listarEventos(): List<Eventos> = listEventos.toList()
}
object ServicePresentadores {
    private val listPresentadores = mutableListOf<Presentadores>()
    fun agregarEventos(eventos: Presentadores) {
        listPresentadores.add(eventos)
    }

    fun obtenerEventosPorId(id: String): Presentadores? = listPresentadores.find { it.idPresentador == id }

    fun actualizarEventos(id: String, eventos: Presentadores){
        for(i in 0 until listPresentadores.size){
            if(listPresentadores[i].idPresentador == id){
                listPresentadores[i] = eventos
            }
        }

    }
    fun eliminarEventos(id: String) {
        listPresentadores.removeIf{it.idPresentador == id}
    }
    fun listarEventos(): List<Presentadores> = listPresentadores.toList()
}
object ServiceClientes {
    private val listClientes = mutableListOf<Clientes>()
    fun agregarEventos(eventos: Clientes) {
        listClientes.add(eventos)
    }

    fun obtenerEventosPorId(id: String): Clientes? = listClientes.find { it.idCliente == id }

    fun actualizarEventos(id: String, eventos: Clientes){
        for(i in 0 until listClientes.size){
            if(listClientes[i].idCliente == id){
                listClientes[i] = eventos
            }
        }

    }
    fun eliminarEventos(id: String) {
        listClientes.removeIf{it.idCliente == id}
    }
    fun listarEventos(): List<Clientes> = listClientes.toList()
}
object ServiceContratos {
    private val listContratos = mutableListOf<Contratos>()

    fun agregarEventos(eventos: Contratos) {
        listContratos.add(eventos)
    }

    fun obtenerEventosPorId(id: String): Contratos? = listContratos.find { it.idContratos == id }

    fun actualizarEventos(id: String, eventos: Contratos){
        for(i in 0 until listContratos.size){
            if(listContratos[i].idContratos == id){
                listContratos[i] = eventos
            }
        }

    }
    fun eliminarEventos(id: String) {
        listContratos.removeIf{it.idContratos == id}
    }
    fun listarEventos(): List<Contratos> = listContratos.toList()
}