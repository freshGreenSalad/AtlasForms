package com.example.atlasforms.features.displayAllForms.data

import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import javax.inject.Inject

class AllFormsRepositoryImplimentation @Inject constructor(

): AllFormsRepository {
    override fun GetAllForms(): SuccessState<List<AnswerForm>> {
        return SuccessState.Failure()

    }
}