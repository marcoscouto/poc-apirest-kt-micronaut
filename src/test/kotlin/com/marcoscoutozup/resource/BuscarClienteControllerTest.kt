package com.marcoscoutozup.resource

import com.marcoscoutozup.domain.Cliente
import com.marcoscoutozup.repository.ClienteRepository
import com.marcoscoutozup.response.ClienteResponse
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpStatus
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class BuscarClienteControllerTest {

    var clienteRepository: ClienteRepository = mockk()
    var clienteResponse: ClienteResponse = mockk()
    var cliente: Cliente = mockk()
    lateinit var controller: BuscarClienteController
    var page: Int = Random().nextInt(10) + 1

    @BeforeEach
    fun setup(){
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
        every { clienteRepository.findById(any()) } returns Optional.empty()
        val resultado = controller.buscarClientePorId(String())
        assertEquals(HttpStatus.NOT_FOUND, resultado.status)
        assertTrue(resultado.body() is Map<*,*>)
    }

    @Test
    @DisplayName("Deve buscar cliente por id")
    fun deveBuscarClientePorId() {
        every { clienteRepository.findById(any()) } returns Optional.of(cliente)
        every { cliente.toClientResponse() } returns clienteResponse
        val resultado = controller.buscarClientePorId(String())
        assertEquals(HttpStatus.OK, resultado.status)
        assertTrue(resultado.body() is ClienteResponse)
    }


    @Test
    @DisplayName("Não deve retornar clientes se não forem encontrados")
    fun naoDeveRetornarClientesSeNaoForemEncontrados() {
        every { clienteRepository.findAll(any()) } returns Page.empty()
        val resultado = controller.buscarTodosOsClientes(page)
        assertEquals(HttpStatus.NOT_FOUND, resultado.status)
        assertTrue(resultado.body() is Map<*,*>)
    }

    @Test
    @DisplayName("Deve buscar clientes")
    fun deveBuscarClientes() {
        every { clienteRepository.findAll(any()) } returns Page.of(listOf(cliente), Pageable.from(page), Random().nextLong())
        every { cliente.toClientResponse() } returns clienteResponse
        val resultado = controller.buscarTodosOsClientes(page)
        assertEquals(HttpStatus.OK, resultado.status)
        assertTrue(resultado.body() is Map<*,*>)
    }

}