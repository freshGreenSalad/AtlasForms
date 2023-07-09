package com.example.atlasforms.common.domain

enum class QuestionType(val value: Int) {
	MultiChoice(0),
	TextQuestion(1),
	NumberQuestion(2),
	BooleanQuestion(3),
	RadioQuestion(4);

	companion object {
		fun fromInt(value: Int) = QuestionType.values().first { it.value == value }
	}
}