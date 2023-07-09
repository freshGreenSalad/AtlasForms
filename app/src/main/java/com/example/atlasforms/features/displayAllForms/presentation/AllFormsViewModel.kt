package com.example.atlasforms.features.displayAllForms.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import com.example.atlasforms.features.displayAllForms.domain.UseCaseGetAllForms
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllFormsViewModel @Inject constructor(
    useCaseGetAllForms: UseCaseGetAllForms
): ViewModel() {

    private val _AllForms = mutableStateOf<SuccessState<List<AnswerForm>>>(SuccessState.Loading())
    val AllForms: State<SuccessState<List<AnswerForm>>> = _AllForms

    init {
        _AllForms.value = useCaseGetAllForms.invoke()
        Log.d("usecae",  useCaseGetAllForms.invoke().toString())
    }
}