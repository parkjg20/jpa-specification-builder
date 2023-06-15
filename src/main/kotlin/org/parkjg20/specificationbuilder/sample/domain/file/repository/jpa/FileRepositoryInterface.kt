package org.parkjg20.specificationbuilder.sample.domain.file.repository.jpa

import org.parkjg20.specificationbuilder.domain.file.entity.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepositoryInterface : JpaRepository<File, String> {

}