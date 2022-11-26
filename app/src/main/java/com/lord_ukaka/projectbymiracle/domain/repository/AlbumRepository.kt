package com.lord_ukaka.projectbymiracle.domain.repository

import com.lord_ukaka.projectbymiracle.domain.models.AlbumInfo
import com.lord_ukaka.projectbymiracle.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AlbumRepository {

    fun getAlbums(): Flow<Resource<List<AlbumInfo>>>
}