package org.parkjg20.specificationbuilder.domain.auth.exception

import org.springframework.security.core.AuthenticationException

class InvalidRefreshTokenException: AuthenticationException("Invalid refresh token") {

}