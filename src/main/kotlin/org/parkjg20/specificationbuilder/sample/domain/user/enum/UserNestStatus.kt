package org.parkjg20.specificationbuilder.sample.domain.user.enum

import org.parkjg20.specificationbuilder.domain.common.exception.UnexpectedStatusException
import javax.persistence.AttributeConverter
import javax.persistence.Converter

enum class UserNestStatus(val value: Int) {
    BANNED(-2),
    LEFT(-1),
    JOINED(0)
}

@Converter(autoApply = true)
class UserNestStatusAttributeConverter : AttributeConverter<UserNestStatus, Int> {

    override fun convertToDatabaseColumn(attribute: UserNestStatus?): Int {
        return if (attribute === null) {
            throw UnexpectedStatusException()
        } else {
            attribute.value
        }
    }

    override fun convertToEntityAttribute(dbData: Int?): UserNestStatus {
        return if (dbData === null) {
            throw UnexpectedStatusException()
        } else {
            UserNestStatus.values().first { it.value == dbData }
        }
    }
}