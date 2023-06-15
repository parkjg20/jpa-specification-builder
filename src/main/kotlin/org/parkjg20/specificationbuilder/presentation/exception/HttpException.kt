package org.parkjg20.specificationbuilder.presentation.exception

import org.springframework.http.HttpStatus

abstract class HttpException(
    val statusCode: HttpStatus,
    override val message: String
) : RuntimeException(message) {

}