package org.parkjg20.specificationbuilder.domain.common.type

import org.springframework.stereotype.Component

@Component("toInt")
class ToIntParser : ParserInterface {

    override fun parse(value: String): Comparable<*>? {
        return value.toIntOrNull()
    }
}