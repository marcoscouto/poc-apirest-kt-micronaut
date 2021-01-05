package com.marcoscoutozup.resource

import com.marcoscoutozup.domain.Cliente
import com.marcoscoutozup.repository.ClienteRepository
import com.marcoscoutozup.request.ClienteRequest
import com.marcoscoutozup.response.ClienteResponse
import io.micronaut.http.HttpStatus
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class AtualizarClienteControllerTest {

    var clienteRepository: ClienteRepository = mockk()
    var clientRequest: ClienteRequest = mockk()
    var cliente: Cliente = mockk()
    var clienteResponse: ClienteResponse = mockk()
    lateinit var controller: AtualizarClienteController

    @BeforeEach
    fun setup(){
        controller = AtualizarClienteController(clienteRepository)
    }

    @Test
    @DisplayName("Não deve atualizar cliente se não for encontrado")
    fun naoDeveAtualizarClienteSeNaoForEncontrado() {
        every { clienteRepository.findById(any()) } returns Optional.empty()
        val resultado = controller.atualizarCliente(String(), clientRequest)
        assertEquals(HttpStatus.NOT_FOUND, resultado.status)
        assertTrue(resultado.body() is Map<*, *>)
    }

    @Test
    @DisplayName("Deve atualizar Cliente")
    fun deveAtualizarCliente() {
        every { clienteRepository.findById(any()) } returns Optional.of(cliente)
        every { clienteRepository.update(any()) } returns cliente

        every { cliente.atualizarCliente(any()) } returns Unit
        every { cliente.toClientResponse() } returns clienteResponse

        val resultado = controller.atualizarCliente(String(), clientRequest)
        assertEquals(HttpStatus.OK, resultado.status)
    }
}