package org.parkjg20.specificationbuilder.sample.domain.auth.exception

import org.springframework.security.core.AuthenticationException

class AccessTokenExpiredException: AuthenticationException("ACCESS_TOKEN_EXPIRED") {

}