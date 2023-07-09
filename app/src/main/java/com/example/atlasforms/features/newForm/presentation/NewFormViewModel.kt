package com.example.atlasforms.features.newForm.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import com.example.atlasforms.features.newForm.domain.UseCaseGetNewForm
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewFormViewModel @Inject constructor(
    useCaseGetNewForm: UseCaseGetNewForm
): ViewModel() {

    private val newForm = mutableStateOf<SuccessState<AnswerForm>>(SuccessState.Loading<AnswerForm>())

    init {
        newForm.value = useCaseGetNewForm.invoke()
    }
}