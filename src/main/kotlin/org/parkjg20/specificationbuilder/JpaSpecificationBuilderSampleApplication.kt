package org.parkjg20.specificationbuilder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JpaSpecificationBuilderSampleApplication

fun main(args: Array<String>) {
	runApplication<JpaSpecificationBuilderSampleApplication>(*args)
}
