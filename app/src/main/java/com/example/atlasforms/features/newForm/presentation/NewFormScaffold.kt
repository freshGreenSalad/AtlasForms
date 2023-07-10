package com.example.atlasforms.features.newForm.presentation

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.QuestionType
import com.example.atlasforms.common.domain.SuccessState
import com.example.atlasforms.common.presentation.CircularLoading
import com.example.atlasforms.common.presentation.SendEmailButton
import com.example.atlasforms.features.displayAllForms.presentation.AtlasTopAppBar
import com.example.atlasforms.features.newForm.presentation.viewModel.OnEventNewForm


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewFormScaffold(
    form: State<SuccessState<AnswerForm>>,
    event: (OnEventNewForm) -> Unit,
) {



    Scaffold(
        topBar = {AtlasTopAppBar("NewForm")},
        bottomBar = {AtlasBottomBar(event)}
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it))
        {
            NewformLoadingState(form,event)
        }
    }
}

@Composable
fun AtlasBottomBar(event: (OnEventNewForm) -> Unit) {
    BottomAppBar {
        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { event(OnEventNewForm.saveForm) }) {
                Text("save")
            }
            Button(onClick = { event(OnEventNewForm.sendForm) }) {
                Text("send")
            }
            //SendEmailButton(form)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NewformLoadingState(form: State<SuccessState<AnswerForm>>,updateAnswer: (OnEventNewForm) -> Unit) {
    Column {

        AnimatedContent(
            targetState= form.value,
            transitionSpec= { slideInHorizontally(tween(500),
                initialOffsetX = {fullSize -> fullSize }) with slideOutHorizontally(tween(500))
            }
        ) { transitionState ->
            when (transitionState) {
                is SuccessState.Loading<AnswerForm> -> { CircularLoading()
                }
                is SuccessState.Failure<AnswerForm> -> { Text("Sorry Something Went Wrong")
                }
                is SuccessState.Success<AnswerForm> -> {
                    Form(form,updateAnswer)
                }
            }
        }
    }
}

@Composable
fun Form(form: State<SuccessState<AnswerForm>>,updateAnswer: (OnEventNewForm) -> Unit) {
    LazyColumn(Modifier.fillMaxSize()){
        item{
            form.value.data?.let { Text(it.name) }
        }
        form.value.data?.let {
            items(it.questionList){ question->
                when(question.questionType){
                    QuestionType.MultiChoice -> {MultiChoiceQuestion(question,updateAnswer)}
                    QuestionType.TextQuestion -> {TextQuestion(question,updateAnswer)}
                    QuestionType.NumberQuestion -> {NumberQuestion(question,updateAnswer)}
                    QuestionType.BooleanQuestion -> {BooleanQuestion(question,updateAnswer)}
                    QuestionType.RadioQuestion -> {RadioQuestion(question,updateAnswer)}
                }
            }
        }
    }
}

