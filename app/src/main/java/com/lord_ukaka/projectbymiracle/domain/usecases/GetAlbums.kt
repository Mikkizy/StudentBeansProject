package com.lord_ukaka.projectbymiracle.domain.usecases

import com.lord_ukaka.projectbymiracle.domain.models.AlbumInfo
import com.lord_ukaka.projectbymiracle.domain.repository.AlbumRepository
import com.lord_ukaka.projectbymiracle.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbums @Inject constructor(
    private val repository: AlbumRepository
) {

    operator fun invoke(): Flow<Resource<List<AlbumInfo>>> {
        return repository.getAlbums()
    }
}