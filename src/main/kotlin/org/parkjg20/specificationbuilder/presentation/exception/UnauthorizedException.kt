package org.parkjg20.specificationbuilder.presentation.exception

import org.springframework.http.HttpStatus

class UnauthorizedException(override val message: String) : HttpException(HttpStatus.UNAUTHORIZED, message) {
}