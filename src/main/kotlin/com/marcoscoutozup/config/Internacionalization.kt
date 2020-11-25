package com.marcoscoutozup.config

import io.micronaut.context.MessageSource
import io.micronaut.context.annotation.Factory
import io.micronaut.context.i18n.ResourceBundleMessageSource
import javax.inject.Singleton

@Factory
class Internacionalization {

    @Singleton
    fun messageSource(): MessageSource = ResourceBundleMessageSource("messages")

}