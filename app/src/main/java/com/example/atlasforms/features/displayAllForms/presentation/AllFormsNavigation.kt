package com.example.atlasforms.features.displayAllForms.presentation


import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.atlasforms.Navigation.NavigationDestinations
import com.example.atlasforms.features.displayAllForms.presentation.viewmodel.AllFormsViewModel

fun NavGraphBuilder.AllForms(navController: NavController){
    composable(NavigationDestinations.AllForms) {
        val viewModel = hiltViewModel<AllFormsViewModel>()
        AllFormsScaffold(
            forms = viewModel.AllForms,
            navigateToNewFormPage = {navController.navigate(NavigationDestinations.newForm)},
            event = viewModel::NewFormEvent,
            navigateToUpdateForm = {id ->
                navController.navigate(
                NavigationDestinations.updateForm+"/${id.toString()}"
            )}
        )
    }
}