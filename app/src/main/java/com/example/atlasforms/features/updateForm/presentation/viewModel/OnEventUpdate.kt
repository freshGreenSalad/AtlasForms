package com.example.atlasforms.features.updateForm.presentation.viewModel

import com.example.atlasforms.features.newForm.presentation.viewModel.OnEventNewForm

sealed interface OnEventUpdate{
    data class GetUpdateForm(val id:Int): OnEventUpdate

    object SaveForm :OnEventUpdate

    data class UpdateQuestionAnswer(val answer: String, val id :Int): OnEventUpdate
}
