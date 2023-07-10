package com.example.atlasforms.features.updateForm.presentation.viewModel

sealed interface OnEventUpdate{
    data class GetUpdateForm(val id:Int): OnEventUpdate
}
