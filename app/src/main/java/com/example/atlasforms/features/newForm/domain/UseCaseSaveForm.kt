package com.example.atlasforms.features.newForm.domain

import android.util.Log
import com.example.atlasforms.Di.IoDispatcher
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.features.newForm.data.FormRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UseCaseSaveForm @Inject constructor(
    private val formRepository: FormRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,

    ) {
    operator fun invoke(form: AnswerForm) {
        return runBlocking(
            CoroutineName("Sample CN") + SupervisorJob() + dispatcher
        ) {
            formRepository.saveAnswerForm(form)
            Log.d("in the save form use case","")
        }
    }
}