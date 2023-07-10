package com.example.atlasforms.features.updateForm.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.atlasforms.Navigation.NavigationDestinations
import com.example.atlasforms.features.updateForm.presentation.viewModel.updateViewModel

fun NavGraphBuilder.updateForm(navController: NavController){
    composable(
        route = NavigationDestinations.updateForm+"/{userId}",
        arguments = listOf(navArgument("userId") { type = NavType.StringType },)
        ) {navBackStackEntry ->
        val viewmodel = hiltViewModel<updateViewModel>()
        val id = navBackStackEntry.arguments?.getString("userId")?.toInt()!!
        updateFormScaffold(id, viewmodel::event, viewmodel.newForm)
    }
}