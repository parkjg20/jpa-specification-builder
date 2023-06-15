package org.parkjg20.specificationbuilder.application.board.usecase

import org.parkjg20.specificationbuilder.application.common.dto.OrderRequestDto
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Order

abstract class AbstractGetMany {

    protected fun makeSort(orders: List<OrderRequestDto>): Sort {
        return Sort.by(orders.map {
            val direction = if (it.descending) {
                Sort.Direction.DESC
            } else {
                Sort.Direction.ASC
            }

            Order(direction, it.field)
        })
    }
}