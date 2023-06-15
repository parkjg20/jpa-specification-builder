package org.parkjg20.specificationbuilder.core.specification

data class RangeInt(
    override val start: Int?,
    override val end: Int?,
    override val containsEqual: Boolean = false )
: AbstractRange<Int>(start, end, containsEqual) {}