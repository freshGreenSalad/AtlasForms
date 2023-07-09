package com.example.atlasforms.features.displayAllForms.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import com.example.atlasforms.common.presentation.CircularLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllFormsScaffold(
    forms: State<SuccessState<List<AnswerForm>>>,
) {
    Scaffold(
        topBar = {AtlasTopAppBar("All Forms")},
        floatingActionButton = {AddNewFormFab({ })}
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it))
        {
            AllformsLoadingState(forms)

        }
    }
}

@Composable
fun AnswerFormList(
    answerFormList: List<AnswerForm>
) {
    LazyColumn(){
        items(answerFormList){ form:AnswerForm ->
            Text(form.name)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtlasTopAppBar(title: String) {
    TopAppBar(
        title = {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){Text(title)}},
    )
}

@Composable
fun AddNewFormFab( createNewForm: ()-> Unit) {
    FloatingActionButton(onClick = { createNewForm()}) {
        Icon(Icons.Default.Add, "Add form Icon")
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AllformsLoadingState(screenState: State<SuccessState<List<AnswerForm>>>) {
    Column {
        AnimatedContent(
            targetState= screenState.value,
            transitionSpec= { slideInHorizontally(tween(500),
                initialOffsetX = {fullSize -> fullSize })with slideOutHorizontally(tween(500))
            }
        ) { transitionState ->
            when (transitionState) {
                SuccessState.Loading<List<AnswerForm>>() -> { CircularLoading()}
                SuccessState.Failure<List<AnswerForm>>() -> { Text("Sorry Something Went Wrong")}
                SuccessState.Success<List<AnswerForm>>() -> {AnswerFormList(transitionState.data ?: emptyList<AnswerForm>())}
                else -> {Text("else Branch")}
            }
        }
    }
}
