package com.example.atlasforms.features.newForm.domain

import androidx.room.PrimaryKey
import com.example.atlasforms.Di.IoDispatcher
import com.example.atlasforms.common.data.networkConnectivityManager.ConnectivityObserver
import com.example.atlasforms.common.data.networkConnectivityManager.NetworkConnectivityObserver
import com.example.atlasforms.common.domain.*
import com.example.atlasforms.features.newForm.data.FormRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UseCaseGetNewForm @Inject constructor(
    private val formRepository: FormRepository,
    private val connectivityObserver: NetworkConnectivityObserver,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(): SuccessState<AnswerForm> {
        return runBlocking(
            CoroutineName("Sample CN") + SupervisorJob() + dispatcher
        ) {
            val form = if (connectivityObserver.observe().first() == ConnectivityObserver.Status.Available) {
                formRepository.getMainFormConnected()
            } else {
                formRepository.getMainFormNotConnected()
            }

            if (form == SuccessState.Failure<Form>()){
                return@runBlocking SuccessState.Failure<AnswerForm>()
            }

            if (form.data == null){
                return@runBlocking SuccessState.Failure<AnswerForm>()
            }

            val answerForm = FormToAnswerFormConverter().formToAnswerForm(form.data )

            SuccessState.Success(answerForm)
        }
    }
}

class FormToAnswerFormConverter(){
    fun formToAnswerForm(form: Form):AnswerForm{
        return AnswerForm(
            id = form.id,
            dateCreated = form.dateCreated,
            email = form.email,
            name = form.name,
            isSent = false,
            questionList = listQuestionsToListAnswerQuestions(form.questionList)
        )
    }

    fun listQuestionsToListAnswerQuestions(list: List<Question>): List<AnswerQuestion>{
        val answerList = mutableListOf<AnswerQuestion>()
        for (question in list){
            val answerQuestion = AnswerQuestion(
                id = question.id,
                listOfMultiChoiceQuestions = question.listOfMultiChoiceQuestions,
                chosenMultiChoiceAnswer = "",
                question = question.question,
                answer =  if(QuestionType.fromInt(question.questionType) == QuestionType.BooleanQuestion){"false"}else{""} ,
                isAnswered = false,
                questionNumber = question.questionNumber,
                questionType = QuestionType.fromInt(question.questionType)
            )
            answerList.add(answerQuestion)
        }
        return answerList
    }
}