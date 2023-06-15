package org.parkjg20.specificationbuilder.sample.domain.board.exception

class ModifyingOtherUsersCommentException : RuntimeException("댓글의 작성자가 아닙니다.") {

}