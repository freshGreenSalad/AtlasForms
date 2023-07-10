package com.example.atlasforms.features.updateForm.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlasforms.Di.IoDispatcher
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import com.example.atlasforms.features.newForm.data.FormRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class updateViewModel @Inject constructor(
    private val formRepository: FormRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    ):ViewModel() {
    val scope = viewModelScope

    private val _newForm =mutableStateOf<SuccessState<AnswerForm>>(SuccessState.Loading<AnswerForm>())
    val newForm: State<SuccessState<AnswerForm>> = _newForm

    fun event(event: OnEventUpdate){
        when(event){
            is OnEventUpdate.GetUpdateForm -> {
                scope.launch(dispatcher) {
                    _newForm.value = SuccessState.Success(formRepository.getAnswerFormById(event.id))
                }
            }
        }
    }

}