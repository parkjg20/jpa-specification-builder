package org.parkjg20.specificationbuilder

import org.junit.jupiter.api.Test
import org.parkjg20.specificationbuilder.domain.common.CursorStringParser
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.Resource

@SpringBootTest
class CursorStringParserTests {

	@Resource
	lateinit var stringParser: CursorStringParser

	@Test
	fun cursorStringParser() {

		val instantVal = "2023-03-23T01:00:00.123Z"
		val intVal = "12345"
		val floatVal = "1.1234"

		val plainCursorString = "instantVal desc=${instantVal},intVal asc=${intVal},floatVal desc=${floatVal}"

		println(stringParser.parse(plainCursorString))
	}

}
