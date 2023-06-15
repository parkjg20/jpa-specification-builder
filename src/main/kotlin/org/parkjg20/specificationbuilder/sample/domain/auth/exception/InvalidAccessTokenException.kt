package org.parkjg20.specificationbuilder.sample.domain.auth.exception

import org.springframework.security.core.AuthenticationException

class InvalidAccessTokenException: AuthenticationException("INVALID_ACCESS_TOKEN") {

}