package com.lord_ukaka.projectbymiracle.presentation.photos

import com.lord_ukaka.projectbymiracle.domain.models.AlbumInfo
import com.lord_ukaka.projectbymiracle.utils.Resource

data class PhotosState(
    val albums: List<AlbumInfo> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)