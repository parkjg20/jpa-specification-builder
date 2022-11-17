package com.neoguri.neogurinest.api.application.user.usecase

import com.neoguri.neogurinest.api.application.user.dto.request.UserExistenceCheckDto
import com.neoguri.neogurinest.api.application.user.dto.response.UserExistenceDto

interface UserExistenceCheckUseCaseInterface {

    fun execute(checkDto: UserExistenceCheckDto): UserExistenceDto
}