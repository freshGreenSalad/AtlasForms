package com.example.atlasforms.features.displayAllForms.data

import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import kotlinx.coroutines.flow.Flow

interface AllFormsRepository {
    fun GetAllForms(): SuccessState<Flow<List<AnswerForm>>>

    suspend fun deleteForm(form: AnswerForm)
}