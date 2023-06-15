package org.parkjg20.specificationbuilder.sample.domain.common.exception

open class CursorBuildFailedException(override val message: String?) : RuntimeException(message) {
}