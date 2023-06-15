package org.parkjg20.specificationbuilder.application.common.dto

data class DescribedEnumDto<T>(
    val value: T,
    val description: String
) {
}