package com.example.atlasforms.features.displayAllForms.data

import com.example.atlasforms.common.data.Room.AnswerFormDao
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import javax.inject.Inject

class AllFormsRepositoryImplimentation @Inject constructor(
    private val answerFormDao: AnswerFormDao
): AllFormsRepository {
    override fun GetAllForms(): SuccessState<List<AnswerForm>> {
        return try {
            SuccessState.Success(answerFormDao.getall())
        } catch (e:Exception){
            SuccessState.Failure<List<AnswerForm>>()
        }
    }
}