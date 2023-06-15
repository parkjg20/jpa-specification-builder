package org.parkjg20.specificationbuilder.domain.file.repository

import com.neoguri.neogurinest.api.domain.common.repository.AggregateRootRepository
import com.neoguri.neogurinest.api.domain.file.entity.File

interface FileEntityRepositoryInterface : AggregateRootRepository<File, String> {
    fun findByIdOrFail(id: String): File
}