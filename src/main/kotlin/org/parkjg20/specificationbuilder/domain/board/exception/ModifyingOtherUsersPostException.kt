package org.parkjg20.specificationbuilder.domain.board.exception

class ModifyingOtherUsersPostException : RuntimeException("게시글의 작성자가 아닙니다.") {

}