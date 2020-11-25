package com.marcoscoutozup.exception

import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpResponse
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.validation.exceptions.ConstraintExceptionHandler
import javax.inject.Singleton
import javax.validation.ConstraintViolationException

@Singleton
@Replaces(ConstraintExceptionHandler::class)
class ConstraintViolationExceptionHandler: ExceptionHandler<ConstraintViolationException, HttpResponse<*>>{

    override fun handle(request: io.micronaut.http.HttpRequest<*>, e: ConstraintViolationException): HttpResponse<*> {
        val error = mapOf("messages" to e.constraintViolations.map { x -> x.message }.toList())
        return HttpResponse.badRequest(error)
    }

}

