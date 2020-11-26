package com.marcoscoutozup.resource

import com.marcoscoutozup.repository.ClienteRepository
import com.marcoscoutozup.request.ClienteRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.QueryValue
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.validation.Valid

@Controller("/clientes")
@Validated
class AtualizarClienteController(val repository: ClienteRepository) {

    val log = LoggerFactory.getLogger(AtualizarClienteController::class.java)

    @Put("/{id}")
    fun atualizarCliente(@QueryValue id: String,
                         @Body @Valid clientRequest: ClienteRequest) : HttpResponse<Any> {

        log.info("Buscando o cliente para atualização pelo id [{}]", id)
        val clienteProcurado = repository.findById(id)

        if(clienteProcurado.isEmpty){
            log.warn("Cliente não encontrado id [{}]", id)
            return HttpResponse.notFound(mapOf("message" to "Cliente não encontrado"))
        }

        val cliente = clienteProcurado.get()
        cliente.atualizarCliente(clientRequest)
        repository.update(cliente)

        log.info("Cliente atualizado, id [{}]", id)
        return HttpResponse.ok(cliente.toClientResponse())
    }
}