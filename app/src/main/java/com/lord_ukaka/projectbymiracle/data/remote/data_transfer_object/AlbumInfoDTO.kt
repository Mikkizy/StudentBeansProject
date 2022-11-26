package com.lord_ukaka.projectbymiracle.data.remote.data_transfer_object

import com.lord_ukaka.projectbymiracle.domain.models.AlbumInfo
import kotlinx.serialization.Serializable

@Serializable
data class AlbumInfoDTO(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
) {
    fun toAlbumInfo(): AlbumInfo {
        return AlbumInfo(
            thumbnailUrl = thumbnailUrl,
            title = title
        )
    }
}