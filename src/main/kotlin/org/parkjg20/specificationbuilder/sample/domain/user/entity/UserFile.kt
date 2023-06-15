package org.parkjg20.specificationbuilder.sample.domain.user.entity

import org.parkjg20.specificationbuilder.domain.file.entity.File
import org.parkjg20.specificationbuilder.domain.user.enum.UserFileType
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user_files")
open class UserFile {
    @EmbeddedId
    open var id: UserFileId? = null

    @Column(name = "file_url", nullable = false)
    open var fileUrl: String? = null

    @Column(name = "type", nullable = false)
    open var type: UserFileType? = null

    companion object {
        fun create(user: User, file: File, type: UserFileType): UserFile {
            val userFile = UserFile()

            val id = UserFileId()
            id.userId = user.id
            id.fileId = file.id

            userFile.id = id
            userFile.fileUrl = file.fileUrl
            userFile.type = type
            return userFile
        }

    }
}