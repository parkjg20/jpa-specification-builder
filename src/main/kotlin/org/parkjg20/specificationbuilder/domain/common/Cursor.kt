package org.parkjg20.specificationbuilder.domain.common

import org.springframework.data.domain.Sort.Order

data class Cursor(
    val order: Order,
    val value: Comparable<Any>
) {
    override fun toString(): String {
        return order.property + " " + order.direction.toString().lowercase() + "=" + value
    }

}
