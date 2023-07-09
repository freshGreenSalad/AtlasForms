package com.example.atlasforms.features.newForm.presentation.viewModel

sealed interface OnEventNewForm {

    object saveForm:OnEventNewForm
    object sendForm:OnEventNewForm

    data class updateQuestionAnswer(val answer: String, val id :Int): OnEventNewForm
}