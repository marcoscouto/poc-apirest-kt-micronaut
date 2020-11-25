package com.marcoscoutozup.validator.cpf

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@Constraint(validatedBy = [CpfValidator::class])
@Target(FIELD)
@Retention(RUNTIME)
annotation class Cpf(
        val message: String = "Cpf inválido",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = []
)
