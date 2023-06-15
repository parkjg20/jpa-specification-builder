package org.parkjg20.specificationbuilder.presentation.exception

import org.springframework.http.HttpStatus

class ConflictException(override val message: String) : HttpException(HttpStatus.CONFLICT, message) {

}