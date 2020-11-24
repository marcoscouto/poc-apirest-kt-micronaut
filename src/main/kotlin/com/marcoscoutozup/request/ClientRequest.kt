package com.marcoscoutozup.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.marcoscoutozup.domain.Cliente
import io.micronaut.core.annotation.Introspected
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Past

@Introspected
class ClientRequest {

        @field:NotBlank
        lateinit var nome: String

        @field:NotNull
        @field:Past
        @field:JsonFormat(pattern = "dd/MM/yyyy")
        lateinit var dataDeNascimento: LocalDate

        @field:NotBlank
        lateinit var documento: String

        fun toCliente(): Cliente = Cliente(this.nome, this.dataDeNascimento, this.documento)
}