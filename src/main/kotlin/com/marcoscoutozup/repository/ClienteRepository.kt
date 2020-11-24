package com.marcoscoutozup.repository

import com.marcoscoutozup.domain.Cliente
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.PageableRepository

@Repository
interface ClienteRepository : PageableRepository<Cliente, String>