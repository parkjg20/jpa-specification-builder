package org.parkjg20.specificationbuilder.persistence.specification

data class LikeString(
    override val value: String
) : AbstractLike<String>(value) {

}