package com.marcoscoutozup.resource

import com.marcoscoutozup.domain.Cliente
import com.marcoscoutozup.repository.ClienteRepository
import com.marcoscoutozup.response.ClienteResponse
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.MockitoAnnotations.initMocks
import java.util.*

class BuscarClienteControllerTest {

    @Mock
    lateinit var clienteRepository: ClienteRepository

    @Mock
    lateinit var clienteResponse: ClienteResponse

    @Mock
    lateinit var cliente: Cliente

    lateinit var controller: BuscarClienteController

    var page: Int = Random().nextInt(10) + 1

    @BeforeEach
    fun setup(){
        initMocks(this)
        controller = BuscarClienteController(clienteRepository, page)
    }

    @Test
    @DisplayName("Deve lançar exceção se a pagina for zero ou negativa")
    fun deveLancarExcecaoSeAPaginaForZeroOuNegativa() {
        assertThrows(IllegalArgumentException::class.java) { controller.buscarTodosOsClientes(0) }
    }


    @Test
    @DisplayName("Não deve retornar cliente por id se não for encontrado")
    fun naoDeveRetornarClientePorIdSeNaoForEncotrado() {
        `when`(clienteRepository.findById(any())).thenReturn(Optional.empty())
        val resultado = controller.buscarClientePorId(String())
        assertEquals(HttpStatus.NOT_FOUND, resultado.status)
        assertTrue(resultado.body() is Map<*,*>)
    }

    @Test
    @DisplayName("Deve buscar cliente por id")
    fun deveBuscarClientePorId() {
        `when`(clienteRepository.findById(any())).thenReturn(Optional.of(cliente))
        `when`(cliente.toClientResponse()).thenReturn(clienteResponse)
        val resultado = controller.buscarClientePorId(String())
        assertEquals(HttpStatus.OK, resultado.status)
        assertTrue(resultado.body() is ClienteResponse)
    }


    @Test
    @DisplayName("Não deve retornar clientes se não forem encontrados")
    fun naoDeveRetornarClientesSeNaoForemEncontrados() {
        `when`(clienteRepository.findAll(any())).thenReturn(Page.empty())
        val resultado = controller.buscarTodosOsClientes(page)
        assertEquals(HttpStatus.NOT_FOUND, resultado.status)
        assertTrue(resultado.body() is Map<*,*>)
    }

    @Test
    @DisplayName("Deve buscar clientes")
    fun deveBuscarClientes() {
        `when`(clienteRepository.findAll(any())).thenReturn(Page.of(listOf(cliente), Pageable.from(page), Random().nextLong()))
        val resultado = controller.buscarTodosOsClientes(page)
        assertEquals(HttpStatus.OK, resultado.status)
        assertTrue(resultado.body() is Map<*,*>)
    }

}