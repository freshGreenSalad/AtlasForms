package com.example.atlasforms.features.newForm.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.atlasforms.Navigation.NavigationDestinations
import com.example.atlasforms.features.newForm.presentation.viewModel.NewFormViewModel

fun NavGraphBuilder.NewForm(navController: NavController){
    composable(NavigationDestinations.newForm) {
        val viewModel = hiltViewModel<NewFormViewModel>()
        NewFormScaffold(viewModel.newForm, viewModel::NewFormEvent)
    }
}