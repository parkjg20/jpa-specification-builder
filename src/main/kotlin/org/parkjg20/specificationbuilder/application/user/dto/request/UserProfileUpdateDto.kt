package org.parkjg20.specificationbuilder.application.user.dto.request

import com.neoguri.neogurinest.api.domain.user.enum.Gender

data class UserProfileUpdateDto(
    val userId: Int,
    val profileFileId: String?,
    val nickname: String,
    val gender: Gender
) {}