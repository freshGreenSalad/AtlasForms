package com.example.atlasforms.features.newForm.data

import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.Form
import com.example.atlasforms.common.domain.SuccessState

interface FormRepositoryInterface {

    suspend fun getMainFormConnected() : SuccessState<Form>
    suspend fun getMainFormNotConnected() : SuccessState<Form>

    fun saveAnswerForm(form: AnswerForm)


}