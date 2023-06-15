package org.parkjg20.specificationbuilder.sample.application.common.dto

import org.parkjg20.specificationbuilder.presentation.param.OrderDtoBuilder

data class PaginationDto(
    val page: Int,
    val orders: List<OrderRequestDto>,
    val size: Int
) {

    companion object {
        fun of(requestDto: PaginationRequestDto): PaginationDto {
            val orders = OrderDtoBuilder(requestDto.order).build()
            return PaginationDto(requestDto.page - 1, orders, requestDto.size)
        }
    }

}