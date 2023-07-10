package com.example.atlasforms.features.newForm.data

import android.util.Log
import com.example.atlasforms.common.data.Room.AnswerFormDao
import com.example.atlasforms.common.data.Room.FormDao
import com.example.atlasforms.common.data.http.BasicHttpReqests
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.Form
import com.example.atlasforms.common.domain.SuccessState
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FormRepository @Inject constructor(
    private val httpClient: BasicHttpReqests,
    private val formDao: FormDao,
    private val answerFormDao: AnswerFormDao,
): FormRepositoryInterface {

    override suspend fun getMainFormConnected(): SuccessState<Form> {
        val response = httpClient.GetMainForm()

        if (response == SuccessState.Failure<Form>()){
            return SuccessState.Failure()
        }

        return try{
            val httpForm = if (response.data == null){
                Log.d("FormRepository getMainFormConnected try encodetojson", "failure")
                Json.encodeToString(Form())
            } else {
                response.data.bodyAsText()
            }
            val mainForm = Json.decodeFromString<Form>(httpForm)
            replaceMain(mainForm)
            return SuccessState.Success(mainForm)
        } catch(e: Exception){
            return SuccessState.Failure()
        }
    }

    override suspend fun getMainFormNotConnected(): SuccessState<Form> {
        return try {
            val form = formDao.getall().first()
            SuccessState.Success(form)
        } catch (e:Exception){
            SuccessState.Failure()
        }
    }

    private fun replaceMain(form: Form) {
        formDao.nukeTable()
        formDao.insertAll(form)
    }

    override fun saveAnswerForm(form: AnswerForm) {
        answerFormDao.insertAll(form)
    }

    override suspend fun getAnswerFormById(id: Int):AnswerForm {
        return answerFormDao.getbyId(id)
    }
}
