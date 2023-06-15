package org.parkjg20.specificationbuilder.sample.domain.common.type

interface ParserInterface {
    fun parse(value: String): Comparable<*>?
}