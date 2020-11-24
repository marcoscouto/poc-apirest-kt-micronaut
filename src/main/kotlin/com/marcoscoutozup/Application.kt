package com.marcoscoutozup

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.marcoscoutozup")
		.start()
}

