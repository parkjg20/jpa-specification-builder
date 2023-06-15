package org.parkjg20.specificationbuilder.core.specification

import org.parkjg20.specificationbuilder.core.exception.JpsBuilderException

abstract class AbstractRange<Type: Comparable<Type>>(
    override val start: Type?,
    override val end: Type?,
    override val containsEqual: Boolean = false
) : RangeInterface<Type> {

    override fun getRangeType(): RangeType {
        return if (isBetween()) {
            RangeType.BETWEEN
        } else if (isLessThanTo()) {
            RangeType.LESS_THAN
        } else if (isGreaterThanTo()) {
            RangeType.GREATER_THAN
        } else {
            throw JpsBuilderException("예기치 못한 오류 발생")
        }

    }

    override fun isBetween(): Boolean {
        return start !== null && end !== null
    }

    override fun isLessThanTo(): Boolean {
        return start === null && end !== null && !containsEqual
    }

    override fun isGreaterThanTo(): Boolean {
        return start !== null && end === null && !containsEqual
    }

    override fun isContainsEqual(): Boolean {
        return containsEqual
    }
}