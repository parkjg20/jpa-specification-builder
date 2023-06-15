package org.parkjg20.specificationbuilder.core.specification

data class LikeString(
    override val value: String
) : AbstractLike<String>(value) {

}