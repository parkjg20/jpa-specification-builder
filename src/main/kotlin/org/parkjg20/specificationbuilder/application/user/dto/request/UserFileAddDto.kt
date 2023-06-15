package org.parkjg20.specificationbuilder.application.user.dto.request

data class UserFileAddDto(
    val userId: String,
    val fileId: String,
    val fileUrl: String
) {}