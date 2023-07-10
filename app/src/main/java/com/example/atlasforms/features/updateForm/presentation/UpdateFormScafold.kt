package com.example.atlasforms.features.updateForm.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import com.example.atlasforms.features.newForm.presentation.NewformLoadingState
import com.example.atlasforms.features.updateForm.presentation.viewModel.OnEventUpdate

@Composable
fun updateFormScaffold(
    id:Int,
    event:(OnEventUpdate)-> Unit,
    form: State<SuccessState<AnswerForm>>
) {
    LaunchedEffect(Unit){
        event(OnEventUpdate.GetUpdateForm(id))
    }

    NewformLoadingState(form, {})

}