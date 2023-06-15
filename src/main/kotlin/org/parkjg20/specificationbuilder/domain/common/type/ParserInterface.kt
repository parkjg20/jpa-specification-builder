package org.parkjg20.specificationbuilder.domain.common.type

interface ParserInterface {
    fun parse(value: String): Comparable<*>?
}