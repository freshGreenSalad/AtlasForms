package com.example.atlasforms.features.displayAllForms.domain

import com.example.atlasforms.Di.IoDispatcher
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import com.example.atlasforms.features.displayAllForms.data.AllFormsRepositoryImplimentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UseCaseGetAllForms @Inject constructor(
    private val allFormsRepository: AllFormsRepositoryImplimentation,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(): SuccessState<List<AnswerForm>> {
        return runBlocking(
            CoroutineName("Sample CN") + SupervisorJob() + dispatcher
        ) {

            allFormsRepository.GetAllForms()
                SuccessState.Success(emptyList<AnswerForm>())
        }
    }
}