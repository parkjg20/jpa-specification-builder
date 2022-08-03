package com.neoguri.neogurinest.api.domain.board.enum

enum class BoardCommentReportStatus(val value: Int) {
    IGNORED(-1),
    CREATED(0),
    CHECKED(1),
    PROCESSED(2)
}