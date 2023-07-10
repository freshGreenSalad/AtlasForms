package com.example.atlasforms.features.newForm.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlasforms.Di.IoDispatcher
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import com.example.atlasforms.features.newForm.domain.UseCaseGetNewForm
import com.example.atlasforms.features.newForm.domain.UseCaseSaveForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewFormViewModel @Inject constructor(
    useCaseGetNewForm: UseCaseGetNewForm,
    private val useCaseSaveForm: UseCaseSaveForm,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
): ViewModel() {

    val scope = viewModelScope

    private val _newForm =
        MutableStateFlow<SuccessState<AnswerForm>>(SuccessState.Loading<AnswerForm>())
    val newForm: StateFlow<SuccessState<AnswerForm>> = _newForm.asStateFlow()

    private lateinit var noStatePushForm :AnswerForm

    init {
        scope.launch(dispatcher) {
            _newForm.value = useCaseGetNewForm.invoke()
            noStatePushForm = _newForm.value.data!!
        }
    } // needs to change to a loading

    fun NewFormEvent(event: OnEventNewForm) {
        when (event) {
            is OnEventNewForm.saveForm -> {
                try{
                    useCaseSaveForm.invoke(noStatePushForm)
                }   catch (_:Exception){}
            }
            is OnEventNewForm.updateQuestionAnswer -> {
                noStatePushForm =
                    noStatePushForm.copy(
                        questionList = noStatePushForm.questionList.map { answerQuestion ->
                            if(answerQuestion.id == event.id) {
                                answerQuestion.copy(
                                    answer = event.answer
                                )
                            } else {answerQuestion}
                        }
                    )
            }
            is OnEventNewForm.sendForm -> {
                Log.d("updated form" , noStatePushForm.toString())
            }
            is OnEventNewForm.isEnabled -> {
                noStatePushForm =
                    noStatePushForm.copy(
                        questionList = noStatePushForm.questionList.map { answerQuestion ->
                            if(answerQuestion.id == event.id) {
                                answerQuestion.copy(
                                    isAnswered = event.enabled
                                )
                            } else {answerQuestion}
                        }
                    )
                _newForm.value.data?.copy(questionList = noStatePushForm.questionList.map { answerQuestion ->
                    if(answerQuestion.id == event.id) {
                        answerQuestion.copy(
                            isAnswered = event.enabled
                        )
                    } else {answerQuestion}
                })
            }
        }
    }
}