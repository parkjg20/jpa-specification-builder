package org.parkjg20.specificationbuilder.sample.domain.board.enum

import org.parkjg20.specificationbuilder.domain.common.exception.UnexpectedStatusException
import javax.persistence.AttributeConverter
import javax.persistence.Converter

enum class BoardPostReportStatus(val value: Int) {
    IGNORED(-1),
    CREATED(0),
    CHECKED(1),
    PROCESSED(2)
}

@Converter(autoApply = true)
class BoardPostReportStatusAttributeConverter : AttributeConverter<BoardPostReportStatus, Int> {

    override fun convertToDatabaseColumn(attribute: BoardPostReportStatus?): Int {
        return if (attribute === null) {
            throw UnexpectedStatusException()
        } else {
            attribute.value
        }
    }

    override fun convertToEntityAttribute(dbData: Int?): BoardPostReportStatus {
        return if (dbData === null) {
            throw UnexpectedStatusException()
        } else {
            BoardPostReportStatus.values().first { it.value == dbData }
        }
    }
}