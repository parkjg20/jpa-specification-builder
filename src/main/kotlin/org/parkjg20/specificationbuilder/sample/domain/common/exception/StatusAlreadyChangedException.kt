package org.parkjg20.specificationbuilder.sample.domain.common.exception

class StatusAlreadyChangedException : RuntimeException("요청이 이미 처리되었습니다.") {
}