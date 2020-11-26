package com.marcoscoutozup.resource

import com.marcoscoutozup.domain.Cliente
import com.marcoscoutozup.repository.ClienteRepository
import com.marcoscoutozup.request.ClienteRequest
import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.MockitoAnnotations.initMocks
import java.util.*

class AtualizarClienteControllerTest {

    @Mock
    lateinit var clienteRepository: ClienteRepository

    @Mock
    lateinit var clientRequest: ClienteRequest

    @Mock
    lateinit var cliente: Cliente

    lateinit var controller: AtualizarClienteController

    @BeforeEach
    fun setup(){
        initMocks(this)
        controller = AtualizarClienteController(clienteRepository)
    }

    @Test
    @DisplayName("Não deve atualizar cliente se não for encontrado")
    fun naoDeveAtualizarClienteSeNaoForEncontrado() {
        `when`(clienteRepository.findById(any())).thenReturn(Optional.empty())
        val resultado = controller.atualizarCliente(String(), clientRequest)
        assertEquals(HttpStatus.NOT_FOUND, resultado.status)
        assertTrue(resultado.body() is Map<*, *>)
    }

    @Test
    @DisplayName("Deve atualizar Cliente")
    fun deveAtualizarCliente() {
        `when`(clienteRepository.findById(any())).thenReturn(Optional.of(cliente))
        val resultado = controller.atualizarCliente(String(), clientRequest)
        assertEquals(HttpStatus.OK, resultado.status)
    }
}