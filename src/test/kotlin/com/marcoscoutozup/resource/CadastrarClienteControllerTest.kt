package com.marcoscoutozup.resource

import com.marcoscoutozup.domain.Cliente
import com.marcoscoutozup.repository.ClienteRepository
import com.marcoscoutozup.request.ClienteRequest
import io.micronaut.http.HttpMethod.POST
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CadastrarClienteControllerTest {

    var clienteRepository: ClienteRepository = mockk()
    var clientRequest: ClienteRequest = mockk()
    var cliente: Cliente = mockk()
    lateinit var controller: CadastrarClienteController

    @BeforeEach
    fun setup(){
        controller = CadastrarClienteController(clienteRepository, String(), String(), 8080)
    }

    @Test
    @DisplayName("Deve cadastrar cliente")
    fun deveCadastrarCliente() {
        every { clienteRepository.save(any()) } returns cliente
        every { clientRequest.toCliente() } returns cliente
        every { cliente.id } returns String()
        val resultado = controller.cadastrarCliente(clientRequest, HttpRequest.create(POST, "uri"))
        assertEquals(HttpStatus.CREATED, resultado.status)
        assertNotNull(resultado.headers["Location"])
    }
}