package com.example.atlasforms.features.displayAllForms.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlasforms.Di.IoDispatcher
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import com.example.atlasforms.features.displayAllForms.data.AllFormsRepositoryImplimentation
import com.example.atlasforms.features.displayAllForms.domain.UseCaseGetAllForms
import com.example.atlasforms.features.newForm.presentation.viewModel.OnEventNewForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllFormsViewModel @Inject constructor(
    useCaseGetAllForms: UseCaseGetAllForms,
    private val allFormsRepositoryImplimentation: AllFormsRepositoryImplimentation,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {

    val scope = viewModelScope

    private val _AllForms = mutableStateOf<SuccessState<Flow<List<AnswerForm>>>>(SuccessState.Loading())
    val AllForms: State<SuccessState<Flow<List<AnswerForm>>>> = _AllForms

    init {
        _AllForms.value = useCaseGetAllForms.invoke()
        Log.d("usecae",  useCaseGetAllForms.invoke().toString())
    }

    fun NewFormEvent(event: OnEventAllForm) {
        when (event) {
            is OnEventAllForm.deleteForm -> {
                viewModelScope.launch (dispatcher) {
                    allFormsRepositoryImplimentation.deleteForm(event.form)
                }
            }
        }
    }
}