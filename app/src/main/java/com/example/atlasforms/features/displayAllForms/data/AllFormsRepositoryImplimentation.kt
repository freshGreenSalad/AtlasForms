package com.example.atlasforms.features.displayAllForms.data

import com.example.atlasforms.common.data.Room.AnswerFormDao
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllFormsRepositoryImplimentation @Inject constructor(
    private val answerFormDao: AnswerFormDao
): AllFormsRepository {
    override fun GetAllForms(): SuccessState<Flow<List<AnswerForm>>> {
        return try {
            SuccessState.Success(answerFormDao.getall())
        } catch (e:Exception){
            SuccessState.Failure<Flow<List<AnswerForm>>>()
        }
    }

    override suspend fun deleteForm(form: AnswerForm) {
        answerFormDao.deleteform(form)
    }
}