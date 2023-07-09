package com.example.atlasforms.features.newForm.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.atlasforms.Navigation.NavigationDestinations

fun NavGraphBuilder.NewForm(navController: NavController){
    composable(NavigationDestinations.newForm) {
        //val viewModel = hiltViewModel<LoginViewModel>()
        NewFormScaffold()
    }
}