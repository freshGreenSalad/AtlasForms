package com.example.atlasforms.features.updateForm.presentation

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.QuestionType
import com.example.atlasforms.common.domain.SuccessState
import com.example.atlasforms.common.presentation.CircularLoading
import com.example.atlasforms.features.displayAllForms.presentation.AtlasTopAppBar
import com.example.atlasforms.features.newForm.presentation.*
import com.example.atlasforms.features.newForm.presentation.NewformLoadingState
import com.example.atlasforms.features.newForm.presentation.viewModel.OnEventNewForm
import com.example.atlasforms.features.updateForm.presentation.questions.*
import com.example.atlasforms.features.updateForm.presentation.viewModel.OnEventUpdate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun updateFormScaffold(
    id:Int,
    event:(OnEventUpdate)-> Unit,
    form: State<SuccessState<AnswerForm>>,
    navigateHome: ()->Unit
) {
    LaunchedEffect(Unit){
        event(OnEventUpdate.GetUpdateForm(id))
    }
    Scaffold(
        topBar = { AtlasTopAppBar(title = "UpdateForm")},
        bottomBar = {AtlasBottomBarUpdates(event,navigateHome,form)}
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)){
            UpdateFormLoadingState(form, event)
        }
    }
}

@Composable
fun AtlasBottomBarUpdates(event: (OnEventUpdate) -> Unit,navigateHome: ()->Unit, form: State<SuccessState<AnswerForm>>) {
    BottomAppBar {
        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                event(OnEventUpdate.SaveForm)
                navigateHome()
            }) {
                Text("save")
            }
            Button(onClick = {
                Log.d("form",form.value.data.toString())
            }) {
                Text("send")
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UpdateFormLoadingState(form: State<SuccessState<AnswerForm>>,updateAnswer: (OnEventUpdate) -> Unit) {
    Column {

        AnimatedContent(
            targetState= form.value,
            transitionSpec= { slideInHorizontally(
                tween(500),
                initialOffsetX = {fullSize -> fullSize }) with slideOutHorizontally(tween(500))
            }
        ) { transitionState ->
            when (transitionState) {
                is SuccessState.Loading<AnswerForm> -> { CircularLoading()
                }
                is SuccessState.Failure<AnswerForm> -> { Text("Sorry Something Went Wrong")
                }
                is SuccessState.Success<AnswerForm> -> {
                    FormUpdates(form,updateAnswer)
                }
            }
        }
    }
}

@Composable
fun FormUpdates(form: State<SuccessState<AnswerForm>>,updateAnswer: (OnEventUpdate) -> Unit) {
    LazyColumn(Modifier.fillMaxSize()){
        item{
            form.value.data?.let { Text(text = it.name, style = MaterialTheme.typography.titleMedium,) }
        }
        form.value.data?.let {
            items(it.questionList){ question->
                when(question.questionType){
                    QuestionType.MultiChoice -> {
                        MultiChoiceQuestionUpdate(question,updateAnswer)
                    }
                    QuestionType.TextQuestion -> {
                        TextQuestionUpdate(question,updateAnswer)
                    }
                    QuestionType.NumberQuestion -> {
                        NumberQuestionUpdate(question,updateAnswer)
                    }
                    QuestionType.BooleanQuestion -> {
                        BooleanQuestionUpdate(question,updateAnswer)
                    }
                    QuestionType.RadioQuestion -> {
                        RadioQuestionUpdate(question,updateAnswer)
                    }
                }
            }
        }
    }
}


