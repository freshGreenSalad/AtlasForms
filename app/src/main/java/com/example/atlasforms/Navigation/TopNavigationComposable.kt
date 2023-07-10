package com.example.atlasforms.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.atlasforms.features.displayAllForms.presentation.AllForms
import com.example.atlasforms.features.newForm.presentation.NewForm
import com.example.atlasforms.features.updateForm.presentation.updateForm

@Composable
fun TopNavigationComposable() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationDestinations.AllForms
    ) {
        AllForms(navController)
        NewForm(navController)
        updateForm(navController)
    }
}
