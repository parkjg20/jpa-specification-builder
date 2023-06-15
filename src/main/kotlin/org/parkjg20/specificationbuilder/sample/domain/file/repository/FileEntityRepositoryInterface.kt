package org.parkjg20.specificationbuilder.sample.domain.file.repository

import org.parkjg20.specificationbuilder.domain.common.repository.AggregateRootRepository
import org.parkjg20.specificationbuilder.domain.file.entity.File

interface FileEntityRepositoryInterface : AggregateRootRepository<File, String> {
    fun findByIdOrFail(id: String): File
}