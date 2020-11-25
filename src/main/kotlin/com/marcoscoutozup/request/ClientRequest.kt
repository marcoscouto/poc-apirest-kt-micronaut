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

        @field:NotBlank(message = "O nome é obrigatório")
        lateinit var nome: String

        @field:NotNull(message = "A data de nascimento é obrigatória")
        @field:Past(message = "A data de nascimento deve ser passado")
        @field:JsonFormat(pattern = "dd/MM/yyyy")
        lateinit var dataDeNascimento: LocalDate

        @field:NotBlank(message = "O documento é obrigatório")
        lateinit var documento: String

        fun toCliente(): Cliente = Cliente(this.nome, this.dataDeNascimento, this.documento)
}