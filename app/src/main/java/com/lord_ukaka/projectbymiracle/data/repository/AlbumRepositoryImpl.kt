package com.lord_ukaka.projectbymiracle.data.repository

import com.lord_ukaka.projectbymiracle.data.remote.AlbumApi
import com.lord_ukaka.projectbymiracle.domain.models.AlbumInfo
import com.lord_ukaka.projectbymiracle.domain.repository.AlbumRepository
import com.lord_ukaka.projectbymiracle.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val api: AlbumApi
): AlbumRepository{
    override fun getAlbums(): Flow<Resource<List<AlbumInfo>>> = flow {
        emit(Resource.Loading())
        delay(2000L)
        try {
            val response = api.getAlbums()
            if (response.isSuccessful) {
                response.body()?.let { albumList ->
                    return@let emit(Resource.Success(albumList.map {
                        it.toAlbumInfo()
                    }))
                } ?:  emit( Resource.Error("An unknown error occurred!"))
            } else {
                emit( Resource.Error("Couldn't fetch data, try again!"))
            }
        } catch (e: Exception) {
           emit( Resource.Error("An error occurred! Check Internet Connection"))
        }
    }
}