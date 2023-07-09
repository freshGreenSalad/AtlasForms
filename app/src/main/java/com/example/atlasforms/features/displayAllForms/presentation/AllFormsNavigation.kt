package com.example.atlasforms.features.displayAllForms.presentation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.atlasforms.Navigation.NavigationDestinations

fun NavGraphBuilder.AllForms(navController: NavController){
    composable(NavigationDestinations.AllForms) {
        //val viewModel = hiltViewModel<LoginViewModel>()
        AllFormsScaffold()
    }
}