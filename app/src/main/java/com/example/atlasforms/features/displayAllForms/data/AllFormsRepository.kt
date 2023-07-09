package com.example.atlasforms.features.displayAllForms.data

import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState

interface AllFormsRepository {
    fun GetAllForms(): SuccessState<List<AnswerForm>>
}