package com.zxc.examen3.Modelo
enum class tipoEventoEnum(
    var descripcion: String
){
    Fiestas_de_cumpleaños("Fiestas_de_cumpleaños"),
    Comunión("Comunión"),
    Bautizo("Bautizo"),
    Eventos_de_empresa("Eventos_de_empresa")

}
enum class especialidadEnum(
    var descripcion: String
){
    Mago("Mago"),
    Payaso("Payaso"),
}
enum class generoEnum(
    var descripcion: String
){
    Masculino("Masculino"),
    Femenino("Femenino"),
    Otro("Otro")
}



enum class formaPagoEnum(
    var descripcion: String
){
    tarjeta("tarjeta"),
    efectivo("efectivo"),
}

enum class EstadoCompraEnum(
    var descripcion: String
){
    confirmado("tarjeta"),
    pendiente("efectivo"),
    cancelado("cancelado"),
}

data class Eventos(
    var idEventos: String,
    var tipoEvento : tipoEventoEnum,
    var descripcion: String,
    var duracion: Int? = null,
    var precioBase : Float? = null,
    var numAnimadores: Int? = null,
    var FechaEvento: String,
    var ubiEvento: String,

)

data class Presentadores(
    var  idPresentador: String,//1
    var CUIPresentador: String,//2
    var nombrePresentador: String,//3
    var apellidoPaPresentador: String,//4
    var apellidoMaPresentador: String,//5
    var generoPresentador: generoEnum,//6
    var fechaNacimientoPresentador: String,//7
    var fechaIngresoPresentador: String,//8
    var telefonoPresentador: String,//9
    var correoPresentador:String,//10
    var direccionPresentador: String,//11
    var especialidadPresentado: especialidadEnum//12
)
data class Clientes(
    var idCliente: String,//1
    var nombrCliente:String,//2
    var apellidoPaCliente: String,//3
    var apellidoMaCliente: String,//4
    var domicilioCliente: String,//5
    var telefonoCliente: String,//6
    var correoCliente: String,//7
    var fechaRegistroCliente: String,//8
)
open class Contratos(
    var idContratos: String,
    EventoRegistrado: Eventos,
    PresentadorRegistrado: Presentadores,
    ClienteRegistrado: Clientes,
    var lugarEvento: String,
    var fechaContrato: String,
    var formaPago: formaPagoEnum,
    var precioFinalAcordado: Float,
    var descuentoAplicado: Float,
    var observaciones: String,
    var estadoCompra: EstadoCompraEnum
) {

    var EventoRegistrado: Eventos? = EventoRegistrado
    var PresentadorRegistrado: Presentadores? = PresentadorRegistrado
    var ClienteRegistrado: Clientes? = ClienteRegistrado

    var codigoEvento : String = ""
    var codigoPresentador: String = ""
    var CUICliente: String = ""
    init {
        this.codigoEvento = EventoRegistrado.idEventos
        this.codigoPresentador = PresentadorRegistrado.idPresentador
        this.CUICliente = ClienteRegistrado.idCliente
    }
}

