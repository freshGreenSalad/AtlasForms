package com.example.atlasforms.features.displayAllForms.presentation

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.atlasforms.common.domain.AnswerForm
import com.example.atlasforms.common.domain.SuccessState
import com.example.atlasforms.common.presentation.CircularLoading
import com.example.atlasforms.features.displayAllForms.presentation.viewmodel.OnEventAllForm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllFormsScaffold(
    forms: State<SuccessState<Flow<List<AnswerForm>>>>,
    navigateToNewFormPage: ()-> Unit,
    event :(OnEventAllForm)->Unit,
    navigateToUpdateForm:(Int)->Unit
    ) {
    Scaffold(
        topBar = {AtlasTopAppBar("All Forms")},
        floatingActionButton = {AddNewFormFab(navigateToNewFormPage)}
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it))
        {
            AllformsLoadingState(forms,event,navigateToUpdateForm)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtlasTopAppBar(
    title: String,
) {
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
fun AllformsLoadingState(
    forms: State<SuccessState<Flow<List<AnswerForm>>>>,
    event :(OnEventAllForm)->Unit,
    navigateToUpdateForm:(Int)->Unit
) {
    Column {
        AnimatedContent(
            targetState= forms.value,
            transitionSpec= { slideInHorizontally(tween(500),
                initialOffsetX = {fullSize -> fullSize })with slideOutHorizontally(tween(500))
            }
        ) { transitionState ->
            when (transitionState) {
                is SuccessState.Loading<Flow<List<AnswerForm>>> -> { CircularLoading()}
                is SuccessState.Failure<Flow<List<AnswerForm>>> -> { Text("Sorry Something Went Wrong")}
                is SuccessState.Success<Flow<List<AnswerForm>>> -> {
                    AnswerFormList(transitionState.data ?: flowOf(emptyList<AnswerForm>()), event,navigateToUpdateForm)
                }
            }
        }
    }
}

@Composable
fun AnswerFormList(
    answerFormList: Flow<List<AnswerForm>>,
    event :(OnEventAllForm)->Unit,
    navigateToUpdateForm:(Int)->Unit
) {
    val scope = answerFormList.collectAsState(initial =  emptyList<AnswerForm>())

    LazyColumn(){
        items(scope.value){ form ->
            FormCard(form, event,navigateToUpdateForm)
        }
    }
}

@Composable
fun FormCard(
    form:AnswerForm,
    event :(OnEventAllForm)->Unit,
    navigateToUpdateForm:(Int)->Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
        .clip(RoundedCornerShape(8.dp))
        .background(color = MaterialTheme.colorScheme.secondaryContainer)
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(form.name, modifier = Modifier.padding(4.dp))
            horizontalLine()
            Box(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth()) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(modifier = Modifier.weight(.6f)) {
                            Text("Number of questions in form", modifier = Modifier.padding(4.dp))
                            Text("Creation Date", modifier = Modifier.padding(4.dp))
                        }
                        Column(modifier = Modifier.weight(.4f)) {
                            Text(
                                form.questionList.size.toString(),
                                modifier = Modifier.padding(4.dp)
                            )
                            Text(form.dateCreated, modifier = Modifier.padding(4.dp))
                        }
                    }
                }
            }
            horizontalLine()
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp)
                    )
            ) {Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                Button(modifier = Modifier.padding(4.dp),onClick = {
                        navigateToUpdateForm(form.id)
                }) {
                    Text("Update")
                }
                Button(modifier = Modifier.padding(4.dp),onClick = { event(OnEventAllForm.deleteForm(form)) }) {
                    Text("Delete")
                }
            }
            }
        }
    }
}

@Composable
fun horizontalLine() {
    Box(
        Modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .height(1.dp)
    )
}
