package org.parkjg20.specificationbuilder.domain.auth.exception

import org.springframework.security.core.AuthenticationException

class RefreshTokenExpiredException: AuthenticationException("Refresh token is expired") {

}