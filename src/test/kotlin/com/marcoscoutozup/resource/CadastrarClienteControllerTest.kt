package com.marcoscoutozup.resource

import com.marcoscoutozup.domain.Cliente
import com.marcoscoutozup.repository.ClienteRepository
import com.marcoscoutozup.request.ClienteRequest
import io.micronaut.http.HttpMethod.POST
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import java.util.*

class CadastrarClienteControllerTest {

    @Mock
    lateinit var clienteRepository: ClienteRepository

    @Mock
    lateinit var clientRequest: ClienteRequest

    @Mock
    lateinit var cliente: Cliente

    lateinit var controller: CadastrarClienteController

    @BeforeEach
    fun setup(){
        initMocks(this)
        controller = CadastrarClienteController(clienteRepository, String(), String(), 8080)
    }

    @Test
    @DisplayName("Deve cadastrar cliente")
    fun deveCadastrarCliente() {
        `when`(clientRequest.toCliente()).thenReturn(cliente)
        `when`(cliente.id).thenReturn(String())
        val resultado = controller.cadastrarCliente(clientRequest, HttpRequest.create(POST, "uri"))
        assertEquals(HttpStatus.CREATED, resultado.status)
        assertNotNull(resultado.headers["Location"])
    }
}