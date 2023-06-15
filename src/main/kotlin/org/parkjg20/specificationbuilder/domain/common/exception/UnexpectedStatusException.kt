package org.parkjg20.specificationbuilder.domain.common.exception

import org.parkjg20.specificationbuilder.core.exception.JpsBuilderException

class UnexpectedStatusException : JpsBuilderException("잘못된 상태값이 감지되었습니다.") {
}