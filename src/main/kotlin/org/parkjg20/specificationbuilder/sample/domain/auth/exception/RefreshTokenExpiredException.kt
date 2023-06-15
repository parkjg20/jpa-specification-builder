package org.parkjg20.specificationbuilder.sample.domain.auth.exception

import org.springframework.security.core.AuthenticationException

class RefreshTokenExpiredException: AuthenticationException("Refresh token is expired") {

}