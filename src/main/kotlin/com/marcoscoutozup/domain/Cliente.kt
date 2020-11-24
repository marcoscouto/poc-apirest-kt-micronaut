package com.marcoscoutozup.domain

import com.marcoscoutozup.request.ClientRequest
import com.marcoscoutozup.response.ClienteResponse
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Past

@Entity
data class Cliente (

        @field:NotBlank
        var nome: String,

        @field:NotNull
        @field:Past
        var dataDeNascimento: LocalDate,

        @field:NotBlank
        var documento: String,

) {

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        lateinit var id: String

        @CreationTimestamp
        lateinit var criadoEm: LocalDateTime

        fun toClientResponse(): ClienteResponse =
                ClienteResponse(this.id, this.nome, this.dataDeNascimento, this.documento)

        fun atualizarCliente(clientRequest: ClientRequest) {
                this.nome = clientRequest.nome
                this.dataDeNascimento = clientRequest.dataDeNascimento
                this.documento = clientRequest.documento
        }

}