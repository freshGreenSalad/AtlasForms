package com.example.atlasforms.features.updateForm.presentation.questions

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.atlasforms.common.domain.AnswerQuestion
import com.example.atlasforms.features.updateForm.presentation.viewModel.OnEventUpdate
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun MultiChoiceQuestionUpdate(question: AnswerQuestion, updateAnswer: (OnEventUpdate) -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(Modifier.padding(4.dp)){ Text(question.questionNumber.toString()+".") }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(question.question,modifier = Modifier.padding(4.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .height(1.dp))

            val answer = remember { mutableStateListOf<String>( *(try {
                        Json.decodeFromString<List<String>>(question.answer)
                    }catch(e:Exception){
                        emptyList<String>()
                    }.toTypedArray()
                    ) )}
            for (option in question.listOfMultiChoiceQuestions) {
                MultiChoiceOptionUpdate(
                    optionName = option.question,
                    selected = answer.contains(option.question),
                    onClick = {
                        if (answer.contains(it)){
                            answer.remove(it)
                        } else answer.add(it)
                        updateAnswer(
                            OnEventUpdate.UpdateQuestionAnswer(
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
fun MultiChoiceOptionUpdate(optionName: String, selected:Boolean, onClick:(String)->Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextQuestionUpdate(question: AnswerQuestion, updateAnswer: (OnEventUpdate) -> Unit ) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(Modifier.padding(4.dp)){ Text(question.questionNumber.toString()+".") }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val answer = remember { mutableStateOf(question.answer) }
            Text(text = question.question, modifier = Modifier.padding(4.dp), )
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .height(1.dp))
            TextField(modifier = Modifier.fillMaxWidth(), value = answer.value, onValueChange = {
                answer.value = it
                updateAnswer(OnEventUpdate.UpdateQuestionAnswer(it,  question.id))
            })
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberQuestionUpdate(question: AnswerQuestion, updateAnswer: (OnEventUpdate) -> Unit ) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        val focusManager = LocalFocusManager.current
        val answer = remember { mutableStateOf(question.answer) }
        Box(Modifier.padding(4.dp)){ Text(question.questionNumber.toString()+".") }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = question.question, modifier = Modifier.padding(4.dp), )
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .height(1.dp))
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
                    updateAnswer(OnEventUpdate.UpdateQuestionAnswer(it, question.id))
                }
            )
        }
    }
}

@Composable
fun BooleanQuestionUpdate(question: AnswerQuestion, updateAnswer: (OnEventUpdate) -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        val answer = remember { mutableStateOf(question.answer) }

        Box(Modifier.padding(4.dp)){ Text(question.questionNumber.toString()+".") }

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {

            Text(text = question.question, modifier = Modifier.padding(4.dp),)
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .height(1.dp))

            Column(Modifier.selectableGroup()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(2.dp), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = answer.value == "true",
                        onClick = {
                            answer.value = "true"
                            updateAnswer(
                                OnEventUpdate.UpdateQuestionAnswer(
                                    answer.value,
                                    question.id
                                )
                            )
                        },
                    )
                    Text("true")
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(2.dp), verticalAlignment = Alignment.CenterVertically) {

                    RadioButton(
                        selected = answer.value == "false",
                        onClick = {
                            answer.value = "false"
                            updateAnswer(
                                OnEventUpdate.UpdateQuestionAnswer(
                                    answer.value,
                                    question.id
                                )
                            )
                        },
                    )
                    Text("false")
                }
            }
        }
    }
}

@Composable
fun RadioQuestionUpdate(question: AnswerQuestion, updateAnswer: (OnEventUpdate) -> Unit) {

    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {

        Box(Modifier.padding(4.dp)){ Text(question.questionNumber.toString()+".") }

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(question.question,modifier = Modifier.padding(4.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .height(1.dp))


            val answer = remember { mutableStateOf(question.answer) }
            for (option in question.listOfMultiChoiceQuestions) {
                RadioOptionUpdate(
                    optionName = option.question,
                    selected = option.question == answer.value,
                    onClick = {
                        answer.value = it
                        updateAnswer(
                            OnEventUpdate.UpdateQuestionAnswer(
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
fun RadioOptionUpdate(optionName: String, selected:Boolean, onClick:(String)->Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(2.dp), verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selected,
            onClick = { onClick(optionName) }
        )
        Text(optionName)
    }
}
