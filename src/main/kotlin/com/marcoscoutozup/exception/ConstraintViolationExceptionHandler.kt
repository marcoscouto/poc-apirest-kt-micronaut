package com.marcoscoutozup.exception

import io.micronaut.context.MessageSource
import io.micronaut.context.annotation.Replaces
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpResponse
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.validation.exceptions.ConstraintExceptionHandler
import java.util.*
import javax.inject.Singleton
import javax.validation.ConstraintViolationException

@Singleton
@Replaces(ConstraintExceptionHandler::class)
class ConstraintViolationExceptionHandler(val messageSource: MessageSource, @Value("\${message.default}") val defaultMessage: String): ExceptionHandler<ConstraintViolationException, HttpResponse<*>>{

    override fun handle(request: io.micronaut.http.HttpRequest<*>, e: ConstraintViolationException): HttpResponse<*> {
        val error = mapOf("messages" to e.constraintViolations.map { violation -> messageSource.getMessage(violation.message,
                MessageSource.MessageContext.of(Locale.getDefault()),
                defaultMessage) }.toList())

        return HttpResponse.badRequest(error)
    }

}

