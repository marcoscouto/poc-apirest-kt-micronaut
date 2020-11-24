package com.marcoscoutozup.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class ClienteResponse (

        val id: String,

        val nome: String,

        @JsonFormat(pattern = "dd/MM/yyyy")
        val dataDeNascimento: LocalDate,

        val documento: String
)