package org.parkjg20.specificationbuilder.domain.user.repository

import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository
import org.parkjg20.specificationbuilder.domain.user.entity.User
import java.util.*

interface UserEntityRepositoryInterface : AggregateRootRepository<User, Int> {

    fun checkExistsByEmail(email: String): Boolean

    fun findByIdOrFail(id: Int): User

    fun findByEmail(email: String): Optional<User>

    fun findByEmailOrFail(email: String): User

}