package com.marcoscoutozup.validator.cpf

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext

class CpfValidator: ConstraintValidator<Cpf, String> {

    override fun isValid(value: String?, annotationMetadata: AnnotationValue<Cpf>, context: ConstraintValidatorContext): Boolean =
            value != null && value.matches(Regex("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}\$"))
}