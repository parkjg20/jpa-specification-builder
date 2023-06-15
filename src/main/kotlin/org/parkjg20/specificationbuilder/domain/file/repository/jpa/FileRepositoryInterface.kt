package org.parkjg20.specificationbuilder.domain.file.repository.jpa

import com.neoguri.neogurinest.api.domain.file.entity.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepositoryInterface : JpaRepository<File, String> {

}