package com.example.atlasforms.features.displayAllForms.presentation.viewmodel

import com.example.atlasforms.common.domain.AnswerForm

sealed interface OnEventAllForm {

    //object saveForm:OnEventAllForm

    data class deleteForm(val form: AnswerForm): OnEventAllForm
}