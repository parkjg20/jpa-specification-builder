package org.parkjg20.specificationbuilder.sample.application.common.dto

data class DescribedEnumDto<T>(
    val value: T,
    val description: String
) {
}