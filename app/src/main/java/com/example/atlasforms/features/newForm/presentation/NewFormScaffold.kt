package com.example.atlasforms.features.newForm.presentation

import android.widget.RadioGroup
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.AnswerQuestion
import com.example.atlasforms.common.domain.QuestionType
import com.example.atlasforms.common.domain.SuccessState
import com.example.atlasforms.common.presentation.CircularLoading
import com.example.atlasforms.features.displayAllForms.presentation.AtlasTopAppBar
import com.example.atlasforms.features.newForm.presentation.viewModel.OnEventNewForm
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
fun AtlasBottomBar( event: (OnEventNewForm) -> Unit,) {
    BottomAppBar() {
        Button(onClick = { event(OnEventNewForm.sendForm) }) {
            Text("send")
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

@Composable
fun MultiChoiceQuestion(question:AnswerQuestion,updateAnswer: (OnEventNewForm) -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(question.question)
            val answer = remember { mutableStateListOf<String>() }
            for (option in question.listOfMultiChoiceQuestions) {
                MultiChoiceOption(
                    optionName = option.question,
                    selected = answer.contains(option.question),
                    onClick = {
                        if (answer.contains(it)){
                            answer.remove(it)
                        } else answer.add(it)
                        updateAnswer(
                            OnEventNewForm.updateQuestionAnswer(
                                Json.encodeToString<List<String>>(answer.toList()),
                                question.id
                            )
                        )
                    },
                )
            }
        }
    }
}

@Composable
fun MultiChoiceOption(optionName: String, selected:Boolean, onClick:(String)->Unit) {
    Row(Modifier.fillMaxWidth().padding(4.dp)) {
        Checkbox(
            modifier = Modifier.clickable { onClick(optionName)},
            checked = selected,
            onCheckedChange = null // null recommended for accessibility with screenreaders
        )
        Text(
            text = optionName,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun TextQuestion(question:AnswerQuestion,updateAnswer: (OnEventNewForm) -> Unit ) {

    QuestionCard(
        questionId = question.id,
        question = question.question,
        updateAnswer = updateAnswer
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberQuestion(question:AnswerQuestion,updateAnswer: (OnEventNewForm) -> Unit ) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        val focusManager = LocalFocusManager.current
        val answer = remember { mutableStateOf("") }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = question.question, modifier = Modifier.padding(4.dp), )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = answer.value,
                keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    answer.value = it
                    updateAnswer(OnEventNewForm.updateQuestionAnswer(it, question.id))
                }
            )
        }
    }
}

@Composable
fun BooleanQuestion(question:AnswerQuestion,updateAnswer: (OnEventNewForm) -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        val answer = remember { mutableStateOf(question.answer) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = question.question, modifier = Modifier.padding(4.dp),)

            Column(Modifier.selectableGroup()) {
                Row() {
                    Text("true")
                    RadioButton(
                        selected = answer.value == "true",
                        onClick = {
                            answer.value = "true"
                            updateAnswer(
                                OnEventNewForm.updateQuestionAnswer(
                                    answer.value,
                                    question.id
                                )
                            )
                        },
                    )
                }
                Row() {
                    Text("false")
                    RadioButton(
                        selected = answer.value == "false",
                        onClick = {
                            answer.value = "false"
                            updateAnswer(
                                OnEventNewForm.updateQuestionAnswer(
                                    answer.value,
                                    question.id
                                )
                            )
                        },
                    )
                }
            }

        }
    }
}

@Composable
fun RadioQuestion(question:AnswerQuestion,updateAnswer: (OnEventNewForm) -> Unit) {

    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(question.question)
            val answer = remember { mutableStateOf("") }
            for (option in question.listOfMultiChoiceQuestions) {
                RadioOption(
                    optionName = option.question,
                    selected = option.question == answer.value,
                    onClick = {
                        answer.value = it
                        updateAnswer(
                        OnEventNewForm.updateQuestionAnswer(
                            answer.value,
                            question.id
                        )
                        )
                    },
                )
            }
        }
    }
}

@Composable
fun RadioOption(optionName: String, selected:Boolean, onClick:(String)->Unit) {
    Row() {
        Text(optionName)
        RadioButton(
            selected = selected,
            onClick = { onClick(optionName) }
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionCard(
    questionId:Int,
    question: String,
    updateAnswer: (OnEventNewForm) -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val answer = remember { mutableStateOf("") }
            Text(text = question, modifier = Modifier.padding(4.dp), )
            TextField(modifier = Modifier.fillMaxWidth(), value = answer.value, onValueChange = {
                answer.value = it
                updateAnswer(OnEventNewForm.updateQuestionAnswer(it, questionId))
            })
        }
    }
}