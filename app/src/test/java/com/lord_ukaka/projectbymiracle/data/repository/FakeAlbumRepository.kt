package com.lord_ukaka.projectbymiracle.data.repository

import com.lord_ukaka.projectbymiracle.domain.models.AlbumInfo
import com.lord_ukaka.projectbymiracle.domain.repository.AlbumRepository
import com.lord_ukaka.projectbymiracle.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAlbumRepository : AlbumRepository {

    private val albumInfos = mutableListOf<AlbumInfo>()

    private var shouldReturnNetworkError = false

    /**
     * Returns a boolean depending on the network state of the device.
     */
    private fun shouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override fun getAlbums(): Flow<Resource<List<AlbumInfo>>> {
        return if (shouldReturnNetworkError) {
            flow {
                emit(Resource.Error("An error occurred!"))
            }
        } else {
            flow {
                emit(Resource.Success(albumInfos))
                shouldReturnNetworkError(false)
            }
        }
    }
}