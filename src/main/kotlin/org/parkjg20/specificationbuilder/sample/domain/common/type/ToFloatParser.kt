package org.parkjg20.specificationbuilder.sample.domain.common.type

import org.springframework.stereotype.Component

@Component("toFloat")
class ToFloatParser : ParserInterface {

    override fun parse(value: String): Comparable<*>? {
        return value.toFloatOrNull()
    }
}