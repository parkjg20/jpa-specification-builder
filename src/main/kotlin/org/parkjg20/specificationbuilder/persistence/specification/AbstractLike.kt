package org.parkjg20.specificationbuilder.persistence.specification

abstract class AbstractLike<Type: CharSequence>(override val value: Type): LikeInterface<Type> {

    override fun toString(): String {
        return value.toString()
    }
}