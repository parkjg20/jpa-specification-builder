package org.parkjg20.specificationbuilder.core.exception

class UnexpectedSpecificationTypeException(clazz: Class<*>) : JpsBuilderException("처리할 수 없는 Specification 타입입니다. ${clazz.name}") {
}