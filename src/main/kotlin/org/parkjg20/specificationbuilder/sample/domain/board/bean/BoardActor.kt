package org.parkjg20.specificationbuilder.sample.domain.board.bean

import org.parkjg20.specificationbuilder.domain.user.entity.User
import org.springframework.security.core.GrantedAuthority

data class BoardActor(
    val user: User,
    val authorities: List<GrantedAuthority>
) {
}