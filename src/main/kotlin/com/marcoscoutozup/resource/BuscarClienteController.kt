package com.marcoscoutozup.resource

import com.marcoscoutozup.repository.ClienteRepository
import io.micronaut.context.annotation.Value
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.validation.constraints.Positive

@Controller("/clientes")
@Validated
class BuscarClienteController(val repository: ClienteRepository, @Value("\${quantidade.elementos}") val elementos: Int) {

    val log = LoggerFactory.getLogger(BuscarClienteController::class.java)

    @Get("/page/{page}")
    fun buscarTodosOsClientes(@QueryValue @Positive page: Int) : HttpResponse<Any>{

        log.info("Buscando todos os clientes")

        val clientes = repository.findAll(Pageable.from(page - 1, elementos))

        if(clientes.isEmpty){
            log.warn("Busca por cliente vazia na página [{}]", page)
            return HttpResponse.notFound(mapOf("message" to "Clientes não encontrados"))
        }

        val response = mapOf(
                "resultado" to clientes.map { x -> x.toClientResponse() }.toList(),
                "paginaAtual" to clientes.pageNumber + 1,
                "totalDePaginas" to clientes.totalPages,
                "registrosNaPagina" to clientes.numberOfElements,
                "totalDeRegistros" to clientes.totalSize
        )

        return HttpResponse.ok(response)
    }

    @Get("/{id}")
    fun buscarClientePorId(@QueryValue id: String) : HttpResponse<Any>{

        log.info("Buscando o cliente pelo id [{}]", id)
        val cliente = repository.findById(id)

        if(cliente.isEmpty){
            log.warn("Cliente não encontrado id [{}]", id)
            return HttpResponse.notFound(mapOf("message" to "Cliente não encontrado"))
        }

        log.info("Retornando cliente de id [{}]", id)
        return HttpResponse.ok(cliente.get().toClientResponse())
    }

}