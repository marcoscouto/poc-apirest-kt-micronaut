package com.marcoscoutozup

import io.micronaut.runtime.Micronaut.*
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License

@OpenAPIDefinition(
	info = Info(
		title = "Api Rest com Kotlin e Micronaut",
		version = "1.0",
		description = "Demonstração de Api Rest com Kotlin e Micronaut",
		license = License(name = "Licença 1.0", url="https://github.com/marcoscouto"),
		contact = Contact(url = "https://github.com/marcoscouto", name = "Marcos Couto", email = "marcosmartinellicouto@gmail.com")
	)
)
object Application {

	@JvmStatic
	fun main(args: Array<String>) {
		build()
			.args(*args)
			.packages("com.marcoscoutozup")
			.start()
	}

}


