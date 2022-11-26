package com.lord_ukaka.projectbymiracle.presentation.photos

sealed class PhotosEvents {
    data class ShowSnackbar(val message: String): PhotosEvents()
}
