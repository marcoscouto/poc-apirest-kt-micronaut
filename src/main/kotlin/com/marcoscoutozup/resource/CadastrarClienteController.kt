package com.marcoscoutozup.resource

import com.marcoscoutozup.repository.ClienteRepository
import com.marcoscoutozup.request.ClientRequest
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.validation.Valid

@Controller("/clientes")
@Validated
class CadastrarClienteController (val repository: ClienteRepository,
                                  @Value("\${uri.scheme}") val scheme: String,
                                  @Value("\${uri.host}") val host: String,
                                  @Value("\${uri.port}") val port: Int){

    val log = LoggerFactory.getLogger(CadastrarClienteController::class.java)

    @Post
    fun cadastrarCliente(@Body @Valid clientRequest: ClientRequest, httpRequest: HttpRequest<Any>): HttpResponse<Any> {

        log.info("Solicitação de cadastro de cliente")

        val cliente = clientRequest.toCliente()
        repository.save(cliente)

        val uri = UriBuilder.of(httpRequest.uri)
                .scheme(this.scheme)
                .host(this.host)
                .port(this.port)
                .path("/{id}")
                .expand(mutableMapOf("id" to cliente.id))

        log.info("Cliente cadastrado, id [{}]", cliente.id)
        return HttpResponse.created(uri)
    }

}